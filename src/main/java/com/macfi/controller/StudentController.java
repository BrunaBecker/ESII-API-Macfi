package com.macfi.controller;

import com.macfi.exception.EntityNotFoundException;
import com.macfi.exception.UserUnauthorized;
import com.macfi.model.AttendanceStatus;
import com.macfi.model.Classroom;
import com.macfi.model.Waiver;
import com.macfi.model.person.Student;
import com.macfi.modelMapper.modelMapping;
import com.macfi.payload.ClassroomDto;
import com.macfi.payload.StudentDto;
import com.macfi.service.AttendanceStatusService;
import com.macfi.service.ClassroomService;
import com.macfi.service.StudentService;
import com.macfi.service.WaiverService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:8080")
@RestController
@RequestMapping("student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private ClassroomService classroomService;

    @Autowired
    private WaiverService waiverService;

    @Autowired
    private AttendanceStatusService attendanceStatusService;


    @Operation(
            summary = "Get Student REST API",
            description = "Get Student REST API is used to get all post from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 OK"
    )
    @GetMapping
    public ResponseEntity<List<StudentDto>> getStudents() {
        try {
            return ResponseEntity.ok(studentService.getStudents());
        } catch (EntityNotFoundException | UserUnauthorized ae){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @Operation(
            summary = "Create Student REST API",
            description = "Create Student REST API is used to save post into database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )
    @PostMapping
    public ResponseEntity<StudentDto> createStudent(@RequestBody StudentDto studentDto) {

        StudentDto studentDto1;
        try {
            studentDto1 = studentService.createStudent(studentDto);
            return new ResponseEntity<>(studentDto1, HttpStatus.CREATED);
        } catch (EntityNotFoundException | UserUnauthorized ae){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @Operation(
            summary = "Update Student REST API",
            description = "Update Student REST API is used to delete post from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 OK"
    )
    @PutMapping
    public ResponseEntity<StudentDto> updateStudent(@RequestBody StudentDto studentDto) {
        StudentDto studentDto1;

        try {
            studentDto1 = studentService.updateStudent(studentDto);
            return ResponseEntity.ok(studentDto1);
        } catch (EntityNotFoundException | UserUnauthorized ae){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("{identifier}/class")
    public ResponseEntity<StudentDto> addClassroom(@RequestBody ClassroomDto classroomDto, @PathVariable("identifier") String identifier) {
        Student student;

        try {
            student = modelMapping.getInstance().mapToEntity(studentService.getStudentByIdentifier(identifier), Student.class);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(null);
        }
        Classroom c;
        try {
            c = modelMapping.getInstance().mapToEntity(classroomService.getClassroomById(classroomDto.getId()), Classroom.class);
        } catch (EntityNotFoundException | UserUnauthorized ae){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
        student.getClassrooms().add(modelMapping.getInstance().mapToEntity(classroomDto, Classroom.class));
        return ResponseEntity.ok(modelMapping.getInstance().mapToDto(studentService.updateStudent(modelMapping.getInstance().mapToDto(student, StudentDto.class)), StudentDto.class));
    }

    @PutMapping("{identifier}/attendance/{idAttendance}")
    public ResponseEntity<StudentDto> addAttendance(@PathVariable("idAttendance") Long idAttendance, @PathVariable("identifier") String identifier) {
        Student student;
        try {
            student = modelMapping.getInstance().mapToEntity(studentService.getStudentByIdentifier(identifier), Student.class);
        } catch (EntityNotFoundException | UserUnauthorized ae){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
        try {
            AttendanceStatus a = modelMapping.getInstance().mapToEntity(attendanceStatusService.getAttendanceStatusById(idAttendance), AttendanceStatus.class);
            student.getAttendanceStatuses().add(a);
            return ResponseEntity.ok(studentService.updateStudent(modelMapping.getInstance().mapToDto(student, StudentDto.class)));
        } catch (EntityNotFoundException | UserUnauthorized ae){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }

    }

    @PutMapping("{identifier}/waiver/{idWaiver}")
    public ResponseEntity<StudentDto> addWaiver(@PathVariable("idWaiver") Long idWaiver, @PathVariable("identifier") String identifier) {
        Student  student;

        try {
            student = modelMapping.getInstance().mapToEntity(studentService.getStudentByIdentifier(identifier), Student.class);

        } catch (EntityNotFoundException | UserUnauthorized ae){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }

        Waiver w ;
        try {
            w = modelMapping.getInstance().mapToEntity(waiverService.getWaiverById(idWaiver), Waiver.class);
        } catch (EntityNotFoundException | UserUnauthorized ae){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
        if (student != null && w != null) {
            student.getWaivers().add(w);
            return ResponseEntity.ok(studentService.updateStudent(modelMapping.getInstance().mapToDto(student, StudentDto.class)));
        } else {
           return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("{identifier}/class/{idClass}")
    public ResponseEntity<StudentDto> addClassroom(@PathVariable("idClass") Long idClass, @PathVariable("identifier") String identifier) {
        Student student;
        try {
            student = modelMapping.getInstance().mapToEntity(studentService.getStudentByIdentifier(identifier), Student.class);
        } catch (EntityNotFoundException | UserUnauthorized ae){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
        Classroom c;
        try {
           c  = modelMapping.getInstance().mapToEntity(classroomService.getClassroomById(idClass), Classroom.class);
        } catch (EntityNotFoundException | UserUnauthorized ae){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }

        if (student!=null && c !=null) {
           student.getClassrooms().add(c);
           return ResponseEntity.ok(studentService.updateStudent(modelMapping.getInstance().mapToDto(student, StudentDto.class)));
        }
        return ResponseEntity.badRequest().body(null);


    }

    @GetMapping("classroom/{idClass}")
    public ResponseEntity<List<StudentDto>> getStudentsByClassroom(@PathVariable("idClass") Long idClass) {

        try {
            classroomService.getClassroomById(idClass);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(null);
        }
        try {
            List<StudentDto> students = studentService.getStudentsByClassroom(idClass);
            return ResponseEntity.ok(students);
        } catch (EntityNotFoundException | UserUnauthorized ae){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }

    }

    @GetMapping("attendance/{idAttendance}")
    public ResponseEntity<List<StudentDto>> getStudentsByAttendance(@PathVariable("idAttendance") Long idAttendance) {
        try {
            attendanceStatusService.getAttendanceStatusById(idAttendance);
        } catch (EntityNotFoundException | UserUnauthorized ae){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }

        List<StudentDto> students;
        try {
            students = studentService.getStudentsByAttendance(idAttendance);
            return ResponseEntity.ok(students);
        } catch (EntityNotFoundException | UserUnauthorized ae){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }

    }

    @GetMapping("waiver/{idWaiver}")
    public ResponseEntity<StudentDto> getStudentByWaiver(@PathVariable("idWaiver") Long idWaiver) {
        try {
            waiverService.getWaiverById(idWaiver);
        } catch (EntityNotFoundException | UserUnauthorized ae){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }

        try {
            StudentDto student = modelMapping.getInstance().mapToDto(studentService.getStudentByWaiver(idWaiver), StudentDto.class);
            return ResponseEntity.ok(student);
        } catch (EntityNotFoundException | UserUnauthorized ae){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }

    }

    @GetMapping("/{idStudent}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable("idStudent") Long id) {
        StudentDto studentDto;
        try {
            studentDto = studentService.getStudentById(id);
            return ResponseEntity.ok(studentDto);
        } catch (EntityNotFoundException | UserUnauthorized ae){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("byIdentifier/{identifier}")
    public ResponseEntity<StudentDto> getStudentByIdentifier(@PathVariable("identifier") String identifier) {
        StudentDto studentDto;
        try {
            studentDto = studentService.getStudentByIdentifier(identifier);
            return ResponseEntity.ok(studentDto);
        } catch (EntityNotFoundException | UserUnauthorized ae){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("attendanceHappening/{idAttendance}")
    public ResponseEntity<List<StudentDto>> getStudentsByAttendanceHappening(@PathVariable("idAttendance") Long idAttendance) {
        try {
            attendanceStatusService.getAttendanceStatusById(idAttendance);
        } catch (EntityNotFoundException | UserUnauthorized ae){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }

        List<StudentDto> students;
        try {
            students = studentService.getStudentsByAttendanceHappening(idAttendance);
            return ResponseEntity.ok(students);
        } catch (EntityNotFoundException | UserUnauthorized ae){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("email/{email}")
    public ResponseEntity<StudentDto> getStudentByEmail(@PathVariable("email") String email) {
        StudentDto studentDto;
        try {
            studentDto = studentService.getStudentByEmail(email);
            return ResponseEntity.ok(studentDto);
        } catch (EntityNotFoundException | UserUnauthorized ae){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("classroomCode/{code}")
    public ResponseEntity<List<StudentDto>> getStudentsByClassroomCode(@PathVariable("code") String code) {
        try {
            ClassroomDto  c = classroomService.getClassroomByCode(code);
        } catch (EntityNotFoundException | UserUnauthorized ae){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }

        List<StudentDto> students;
        try {
            students = studentService.getStudentsByClassroomCode(code);
            return ResponseEntity.ok(students);
        } catch (EntityNotFoundException | UserUnauthorized ae){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}