package com.macfi.controller;

import com.macfi.exception.EntityNotFoundException;
import com.macfi.exception.UserUnauthorized;
import com.macfi.payload.AttendanceDto;
import com.macfi.payload.ClassroomDto;
import com.macfi.payload.EventDto;
import com.macfi.service.ClassroomService;
import com.macfi.service.ProfessorService;
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
@RequestMapping("classroom")
public class ClassroomController {

    @Autowired
    ClassroomService classroomService;

    @Autowired
    ProfessorService professorService;

    @Autowired
    StudentService studentService;

    @GetMapping
    public ResponseEntity<List<ClassroomDto>> getClassrooms() {
        return ResponseEntity.ok(classroomService.getClassrooms());
    }

    @Operation(
            summary = "Create Classroom REST API",
            description = "Create Classroom REST API is used to save post into database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )
    @PostMapping
    public ResponseEntity<ClassroomDto> createClassroom(@Valid @RequestBody ClassroomDto classroomDto) {
        ClassroomDto classroomDto1;
        try {
            classroomDto1 = classroomService.createClassroom(classroomDto);
            return new ResponseEntity<>(classroomDto1, HttpStatus.CREATED);
        } catch (EntityNotFoundException | UserUnauthorized ae) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }

    }

    @PutMapping
    public ResponseEntity<ClassroomDto> updateClassroom(@Valid @RequestBody ClassroomDto classroomDto) {
        ClassroomDto classroomDto1;
        try {
            classroomDto1 = classroomService.updateClassroom(classroomDto);
            return ResponseEntity.ok(classroomDto1);
        } catch (EntityNotFoundException | UserUnauthorized ae) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }


    @GetMapping("professor/{identifier}") //localhost:8080/classroom/professor/12345670
    public ResponseEntity<List<ClassroomDto>> getClassroomsByRegisterProfessor(@PathVariable("identifier") String identifier) {
        try {
            return ResponseEntity.ok(classroomService.getClassroomByProfessor(identifier));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("{id}")//localhost:8080/classroom/1
    public ResponseEntity<ClassroomDto> getClassroomById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(classroomService.getClassroomById(id));
        } catch (EntityNotFoundException | UserUnauthorized ae) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
    @GetMapping("student/{identifier}") //localhost:8080/classroom/student/123456790
    public ResponseEntity<List<ClassroomDto>> getClassroomsByRegisterStudent(@PathVariable("identifier") String identifier) {
        try {
            return ResponseEntity.ok(classroomService.getClassroomByStudent(identifier));
        } catch (EntityNotFoundException | UserUnauthorized ae) {
            System.out.println(ae.getMessage());
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("setStudent") //localhost:8080/classroom/setStudent?idClassroom=1&idStudent=1
    public ResponseEntity<ClassroomDto> setStudent(@RequestParam("idClassroom") Long idClassroom, @RequestParam("idStudent") Long idStudent) {
        try {
            return ResponseEntity.ok(classroomService.setStudent(idClassroom, idStudent));
        } catch (EntityNotFoundException | UserUnauthorized ae) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }


    @PutMapping("removeStudent") //localhost:8080/classroom/removeStudent?idClassroom=1&idStudent=1
    public ResponseEntity<ClassroomDto> removeStudent(@RequestParam("idClassroom") Long idClassroom, @RequestParam("idStudent") Long idStudent) {
        try {
            return ResponseEntity.ok(classroomService.removeStudent(idClassroom, idStudent));
        } catch (EntityNotFoundException | UserUnauthorized ae) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("setEvent") //localhost:8080/classroom/setEvent?idClassroom=1&idEvent=1
    public ResponseEntity<ClassroomDto> setEvent(@RequestParam("idClassroom") Long idClassroom, @RequestParam Long idEvent) {
        try {
            return ResponseEntity.ok(classroomService.setEvent(idClassroom, idEvent));
        } catch (EntityNotFoundException | UserUnauthorized ae) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("removeEvent") //localhost:8080/classroom/removeEvent?idClassroom=1&idEvent=1
    public ResponseEntity<ClassroomDto> removeEvent(@RequestParam("idClassroom") Long idClassroom, @RequestParam Long idEvent) {
        try {
            return ResponseEntity.ok(classroomService.removeEvent(idClassroom, idEvent));
        } catch (EntityNotFoundException | UserUnauthorized ae) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }


    @Operation(
            summary = "Add/Change Location REST API",
            description = "Add/Change Location REST API is used to add a location to a classroom"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 OK"
    )
    @PutMapping("setLocation") //localhost:8080/classroom/addLocation?idClassroom=1&idLocation=1
    public ResponseEntity<ClassroomDto> setLocation(@RequestParam("idClassroom") Long idClassroom, @RequestParam Long idLocation) {
        try {
            return ResponseEntity.ok(classroomService.setLocation(idClassroom, idLocation));
        } catch (EntityNotFoundException | UserUnauthorized ae) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("setAttendance") //localhost:8080/classroom/setAttendanceStatus?idClassroom=1&idAttendance=1
    public ResponseEntity<ClassroomDto> setAttendance(@RequestParam("idClassroom") Long idClassroom, @RequestParam Long idAttendance) {
        try {
            return ResponseEntity.ok(classroomService.setAttendance(idClassroom, idAttendance));
        } catch (EntityNotFoundException | UserUnauthorized ae) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("addAttendance")
    public ResponseEntity<ClassroomDto> addAttendance(@RequestParam("idClassroom") Long idClassroom, @Valid @RequestBody AttendanceDto attendanceDto) {
        try {
            return ResponseEntity.ok(classroomService.addAttendance(idClassroom, attendanceDto));
        } catch (EntityNotFoundException | UserUnauthorized ae) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("addEvent")
    public ResponseEntity<ClassroomDto> addEvent(@RequestParam("idClassroom") Long idClassroom, @Valid @RequestBody EventDto eventDto) {
        try {
            return ResponseEntity.ok(classroomService.addEvent(idClassroom, eventDto));
        } catch (EntityNotFoundException | UserUnauthorized ae) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }
    //TODO verify if has getClassroomByCode in some place because I have to change to CLassroom to List<Classroom>
}
