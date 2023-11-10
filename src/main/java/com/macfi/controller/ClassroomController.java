package com.macfi.controller;

import com.macfi.payload.ClassroomDto;
import com.macfi.service.ClassroomService;
import com.macfi.service.ProfessorService;
import com.macfi.service.StudentService;
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

    @PostMapping
    public ResponseEntity<ClassroomDto> createClassroom(@Valid @RequestBody ClassroomDto classroomDto) {
        return new ResponseEntity<>(classroomService.createClassroom(classroomDto), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ClassroomDto> updateClassroom(@Valid @RequestBody ClassroomDto classroomDto) {
        return ResponseEntity.ok(classroomService.updateClassroom(classroomDto));
    }


    @GetMapping("professor/{identifier}")
    public ResponseEntity<List<ClassroomDto>> getClassroomsByRegisterProfessor(@PathVariable("identifier") String identifier) {
        return ResponseEntity.ok(classroomService.getClassroomByProfessor(identifier));
    }

    @GetMapping("student/{identifier}")
    public ResponseEntity<List<ClassroomDto>> getClassroomsByRegisterStudent(@PathVariable("identifier") String identifier) {
        return ResponseEntity.ok(classroomService.getClassroomByStudent(identifier));
    }

}
