package com.macfi.controller;

import com.macfi.model.Attendance;
import com.macfi.model.AttendanceStatus;
import com.macfi.model.Classroom;
import com.macfi.model.Waiver;
import com.macfi.model.person.Student;
import com.macfi.modelMapper.modelMapping;
import com.macfi.payload.ClassroomDto;
import com.macfi.payload.StudentDto;
import com.macfi.repository.AttendanceStatusRepository;
import com.macfi.service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private AttendanceStatusRepository attendanceStatusRepository;

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
        return ResponseEntity.ok(studentService.getStudents());
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
        return new ResponseEntity<>(studentService.createStudent(studentDto), org.springframework.http.HttpStatus.CREATED);
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
        return ResponseEntity.ok(studentService.updateStudent(studentDto));
    }

    @PutMapping("{identifier}/class")
    public ResponseEntity<StudentDto> addClassroom(@RequestBody ClassroomDto classroomDto, @PathVariable("identifier") String identifier) {
        Student student = modelMapping.getInstance().mapToEntity(studentService.getStudentByIdentifier(identifier), Student.class);
        student.getClassrooms().add(modelMapping.getInstance().mapToEntity(classroomDto, Classroom.class));
        return ResponseEntity.ok(modelMapping.getInstance().mapToDto(studentService.updateStudent(modelMapping.getInstance().mapToDto(student, StudentDto.class)), StudentDto.class));
    }

    @PutMapping("{identifier}/attendance/{idAttendance}")
    public ResponseEntity<StudentDto> addAttendance(@PathVariable("idAttendance") Long idAttendance, @PathVariable("identifier") String identifier) {
        Student student = modelMapping.getInstance().mapToEntity(studentService.getStudentByIdentifier(identifier), Student.class);
        AttendanceStatus a = modelMapping.getInstance().mapToEntity(attendanceStatusService.getAttendanceStatusById(idAttendance), AttendanceStatus.class);
        if (student != null && a != null) {
            student.getAttendanceStatuses().add(a);
            return ResponseEntity.ok(studentService.updateStudent(modelMapping.getInstance().mapToDto(student, StudentDto.class)));
        } else {
            return null;
        }
    }

    @PutMapping("{identifier}/waiver/{idWaiver}")
    public ResponseEntity<StudentDto> addWaiver(@PathVariable("idWaiver") Long idWaiver, @PathVariable("identifier") String identifier) {
        Student student = modelMapping.getInstance().mapToEntity(studentService.getStudentByIdentifier(identifier), Student.class);
        Waiver w = modelMapping.getInstance().mapToEntity(waiverService.getWaiverById(idWaiver), Waiver.class);
        if (student != null && w != null) {
            student.getWaivers().add(w);
            return ResponseEntity.ok(studentService.updateStudent(modelMapping.getInstance().mapToDto(student, StudentDto.class)));
        } else {
            return null;
        }
    }

    @PutMapping("{identifier}/class/{idClass}")
    public ResponseEntity<StudentDto> addClassroom(@PathVariable("idClass") Long idClass, @PathVariable("identifier") String identifier) {
        Student student = modelMapping.getInstance().mapToEntity(studentService.getStudentByIdentifier(identifier), Student.class);
        if (classroomService.getClassroomById(idClass) != null) {
            student.getClassrooms().add(modelMapping.getInstance().mapToEntity(classroomService.getClassroomById(idClass), Classroom.class));
            return ResponseEntity.ok(studentService.updateStudent(modelMapping.getInstance().mapToDto(student, StudentDto.class)));
        } else {
            return null;
        }

    }

    @GetMapping("classroom/{idClass}")
    public ResponseEntity<List<StudentDto>> getStudentsByClassroom(@PathVariable("idClass") Long idClass) {
        List<StudentDto> students = studentService.getStudentsByClassroom(idClass);
        return ResponseEntity.ok(students);
    }

    @GetMapping("attendance/{idAttendance}")
    public ResponseEntity<List<StudentDto>> getStudentsByAttendance(@PathVariable("idAttendance") Long idAttendance) {
        List<StudentDto> students = studentService.getStudentsByAttendance(idAttendance);
        return ResponseEntity.ok(students);
    }

    @GetMapping("waiver/{idWaiver}")
    public ResponseEntity<StudentDto> getStudentByWaiver(@PathVariable("idWaiver") Long idWaiver) {
        StudentDto student = modelMapping.getInstance().mapToDto(studentService.getStudentByWaiver(idWaiver), StudentDto.class);
        return ResponseEntity.ok(student);
    }

    @GetMapping("/{idStudent}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable("idStudent") Long id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @GetMapping("byIdentifier/{identifier}")
    public ResponseEntity<StudentDto> getStudentByIdentifier(@PathVariable("identifier") String identifier) {
        return ResponseEntity.ok(studentService.getStudentByIdentifier(identifier));
    }

    @GetMapping("attendanceHappening/{idAttendance}")
    public ResponseEntity<List<StudentDto>> getStudentsByAttendanceHappening(@PathVariable("idAttendance") Long idAttendance) {
        List<StudentDto> students = studentService.getStudentsByAttendanceHappening(idAttendance);
        return ResponseEntity.ok(students);
    }

    @GetMapping("email/{email}")
    public ResponseEntity<StudentDto> getStudentByEmail(@PathVariable("email") String email) {
        return ResponseEntity.ok(studentService.getStudentByEmail(email));
    }

    @GetMapping("classroomCode/{code}")
    public ResponseEntity<List<StudentDto>> getStudentsByClassroomCode(@PathVariable("code") String code) {
        List<StudentDto> students = studentService.getStudentsByClassroomCode(code);
        return ResponseEntity.ok(students);
    }
}