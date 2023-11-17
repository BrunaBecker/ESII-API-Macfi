package com.macfi.controller;

import com.macfi.payload.ClassroomDto;
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
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }

    }

    @PutMapping
    public ResponseEntity<ClassroomDto> updateClassroom(@Valid @RequestBody ClassroomDto classroomDto) {
        ClassroomDto classroomDto1;
        try {
            classroomDto1 = classroomService.updateClassroom(classroomDto);
            return ResponseEntity.ok(classroomDto1);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }


    @GetMapping("professor/{identifier}")
    public ResponseEntity<List<ClassroomDto>> getClassroomsByRegisterProfessor(@PathVariable("identifier") String identifier) {
       try  {
              return ResponseEntity.ok(classroomService.getClassroomByProfessor(identifier));
         } catch (Exception e) {
              return ResponseEntity.badRequest().body(null);
         }
    }

    @GetMapping("student/{identifier}")
    public ResponseEntity<List<ClassroomDto>> getClassroomsByRegisterStudent(@PathVariable("identifier") String identifier) {
        try {
            return ResponseEntity.ok(classroomService.getClassroomByStudent(identifier));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("addStudent/{id}")
    public ResponseEntity<ClassroomDto> addStudent(@PathVariable("id") Long id, @RequestBody ClassroomDto classroomDto) {
        try {
            return ResponseEntity.ok(classroomService.addStudent(id, classroomDto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("addProfessor/{id}")
    public ResponseEntity<ClassroomDto> addProfessor(@PathVariable("id") Long id, @RequestBody ClassroomDto classroomDto) {
        try {
            return ResponseEntity.ok(classroomService.addProfessor(id, classroomDto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
