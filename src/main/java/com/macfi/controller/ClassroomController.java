package com.macfi.controller;

import com.macfi.model.Classroom;
import com.macfi.service.ClassroomService;
import com.macfi.service.ProfessorService;
import com.macfi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("classroom")
public class ClassroomController {

    @Autowired
    ClassroomService ClassroomService;

    @Autowired
    ProfessorService professorService;

    @Autowired
    StudentService studentService;

    @GetMapping
    public List<Classroom> getClassrooms() {
        return ClassroomService.getClassrooms();
    }

    @PostMapping
    public Classroom createClassroom(@RequestBody Classroom classroom) {
        return ClassroomService.createClassroom(classroom);
    }

    @PutMapping
    public Classroom updateClassroom(@RequestBody Classroom classroom) {
        return ClassroomService.updateClassroom(classroom);
    }


    @GetMapping("professor/{identifier}")
    public List<Classroom> getClassroomsByRegisterProfessor(@PathVariable("identifier") String identifier) {
        return ClassroomService.getClassroomByProfessor(identifier);
    }

    @GetMapping("student/{identifier}")
    public List<Classroom> getClassroomsByRegisterStudent(@PathVariable("identifier") String identifier) {
        return ClassroomService.getClassroomByStudent(identifier);
    }

}
