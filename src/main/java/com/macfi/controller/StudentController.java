package com.macfi.controller;

import com.macfi.exception.EntityNotFoundException;
import com.macfi.exception.UserUnauthorized;
import com.macfi.payload.AttendanceStatusDto;
import com.macfi.payload.ClassroomDto;
import com.macfi.payload.StudentDto;
import com.macfi.payload.WaiverDto;
import com.macfi.service.ClassroomService;
import com.macfi.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
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
        } catch (EntityNotFoundException | UserUnauthorized ae) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("classroom/{idClass}")
    public ResponseEntity<List<StudentDto>> getStudentsByClassroom(@PathVariable("idClass") Long idClass) {
        try {
            return ResponseEntity.ok(studentService.getStudentsByClassroom(idClass));
        } catch (EntityNotFoundException | UserUnauthorized ae) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("attendance/{idAttendance}")
    public ResponseEntity<List<StudentDto>> getStudentsByAttendance(@PathVariable("idAttendance") Long idAttendance) {
        try {
            return ResponseEntity.ok(studentService.getStudentsByAttendance(idAttendance));
        } catch (EntityNotFoundException | UserUnauthorized ae) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }

    }

    @GetMapping("waiver/{idWaiver}")
    public ResponseEntity<StudentDto> getStudentByWaiver(@PathVariable("idWaiver") Long idWaiver) {
        try {
            return ResponseEntity.ok(studentService.getStudentByWaiver(idWaiver));
        } catch (EntityNotFoundException | UserUnauthorized ae) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }

    }

    @GetMapping("/{idStudent}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable("idStudent") Long id) {
        StudentDto studentDto;
        try {
            studentDto = studentService.getStudentById(id);
            return ResponseEntity.ok(studentDto);
        } catch (EntityNotFoundException | UserUnauthorized ae) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("byIdentifier/{identifier}")
    public ResponseEntity<StudentDto> getStudentByIdentifier(@PathVariable("identifier") String identifier) {
        StudentDto studentDto;
        try {
            studentDto = studentService.getStudentByIdentifier(identifier);
            return ResponseEntity.ok(studentDto);
        } catch (EntityNotFoundException | UserUnauthorized ae) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("attendanceHappening/{idAttendance}")
    public ResponseEntity<List<StudentDto>> getStudentsByAttendanceHappening(@PathVariable("idAttendance") Long idAttendance) {

        try {
            List<StudentDto> students = studentService.getStudentsByAttendanceHappening(idAttendance);
            return ResponseEntity.ok(students);
        } catch (EntityNotFoundException | UserUnauthorized ae) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("email/{email}")
    public ResponseEntity<StudentDto> getStudentByEmail(@PathVariable("email") String email) {
        StudentDto studentDto;
        try {
            studentDto = studentService.getStudentByEmail(email);
            return ResponseEntity.ok(studentDto);
        } catch (EntityNotFoundException | UserUnauthorized ae) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("classroomByCodeClassNameSemester")  //localhost:8080/student/classroomByCodeClassNameSemester?code=1&className=1&semester=1
    public ResponseEntity<List<StudentDto>> getStudentsByClassroomCode(@RequestParam("code") String code, @RequestParam("className") String className, @RequestParam("semester") String semester) {
        try {
            List<StudentDto> students = studentService.getStudentsByClassroomCode(code, className, semester);
            return ResponseEntity.ok(students);
        } catch (EntityNotFoundException | UserUnauthorized ae) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
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
    public ResponseEntity<StudentDto> createStudent(@Valid @RequestBody StudentDto studentDto) {

        StudentDto studentDto1;
        try {
            studentDto1 = studentService.createStudent(studentDto);
            return new ResponseEntity<>(studentDto1, HttpStatus.CREATED);
        } catch (EntityNotFoundException | UserUnauthorized ae) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
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
    public ResponseEntity<StudentDto> updateStudent(@Valid @RequestBody StudentDto studentDto) {
        StudentDto studentDto1;

        try {
            studentDto1 = studentService.updateStudent(studentDto);
            return ResponseEntity.ok(studentDto1);
        } catch (EntityNotFoundException | UserUnauthorized ae) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("addClassroom") //localhost:8080/student/addClassroom?id=1
    public ResponseEntity<StudentDto> addClassroom(@RequestParam("id") Long id, @Valid @RequestBody ClassroomDto classroomDto) {
        StudentDto studentDto;
        try {
            studentDto = studentService.addClassroom(classroomDto, id);
            return ResponseEntity.ok(studentDto);
        } catch (EntityNotFoundException | UserUnauthorized ae) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("setClassroom") //localhost:8080/student/setClassroom?idClassroom=1&id=1
    public ResponseEntity<StudentDto> setClassroom(@RequestParam("idClassroom") Long idClass, @RequestParam("id") Long id) {
        StudentDto studentDto;
        try {
            studentDto = studentService.setClassroom(idClass, id);
            return ResponseEntity.ok(studentDto);
        } catch (EntityNotFoundException | UserUnauthorized ae) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }

    }

    @PutMapping("setAttendanceStatus")  //localhost:8080/student/setAttendanceStatus?idAttendanceStatus=1&id=1
    public ResponseEntity<StudentDto> setAttendanceStatus(@RequestParam("idAttendanceStatus") Long idAttendanceStatus, @RequestParam("id") Long id) {
        StudentDto studentDto;
        try {
            studentDto = studentService.setAttendanceStatus(idAttendanceStatus, id);
            return ResponseEntity.ok(studentDto);
        } catch (EntityNotFoundException | UserUnauthorized ae) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }

    }

    @PutMapping("addAttendanceStatus") //localhost:8080/student/addAttendanceStatus?idStudent=1
    public ResponseEntity<StudentDto> addAttendanceStatus(@Valid @RequestBody AttendanceStatusDto attendanceDto, @RequestParam("id") Long idStudent) {
        StudentDto studentDto;
        try {
            studentDto = studentService.addAttendanceStatus(attendanceDto, idStudent);
            return ResponseEntity.ok(studentDto);
        } catch (EntityNotFoundException | UserUnauthorized ae) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }

    }

    @PutMapping("setWaiver")
    public ResponseEntity<StudentDto> setWaiver(@RequestParam("idWaiver") Long idWaiver, @RequestParam("id") Long id) {
        try {
            StudentDto studentDto = studentService.setWaiver(idWaiver, id);
            return ResponseEntity.ok(studentDto);
        } catch (EntityNotFoundException | UserUnauthorized ae) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("addWaiver")
    public ResponseEntity<StudentDto> addWaiver(@RequestParam("id") Long idStudent, @Valid @RequestBody WaiverDto waiverDto) {
        try {
            StudentDto studentDto = studentService.addWaiver(waiverDto, idStudent);
            return ResponseEntity.ok(studentDto);
        } catch (EntityNotFoundException | UserUnauthorized ae) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }


}