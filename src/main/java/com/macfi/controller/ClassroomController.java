package com.macfi.controller;

import com.macfi.model.Classroom;
import com.macfi.model.person.Professor;
import com.macfi.service.ClassroomService;
import com.macfi.service.ProfessorService;
import com.macfi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("Classrooms")
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
    public Classroom createClassroom(@RequestBody Classroom Classroom) {
        return ClassroomService.createClassroom(Classroom);
    }

    @DeleteMapping("{idClassroom}")
    public void remnoverClassroom(@PathVariable("idClassroom") Long id) {
        //TODO: clean all the related parameters
        ClassroomService.deleteClassroom(id);
    }


    @PutMapping
    public Classroom updateClassroom(@RequestBody Classroom Classroom) {
        return ClassroomService.updateClassroom(Classroom);
    }

    @PutMapping("{registerProfessor}")
    public Professor adicionarClassroom(@RequestBody Classroom Classroom, @PathVariable Integer registerProfessor) {
        Professor professor = professorService.getProfessorByregisterProfessor(registerProfessor);
        professor.getClassrooms().add(Classroom);
        return professorService.updateProfessor(professor);
    }

    @GetMapping("{registerProfessor}")
    public List<Classroom> getClassroomsByregisterProfessor(@PathVariable("registerProfessor") Integer registerProfessor) {
        return ClassroomService.getClassroomByregisterProfessor(registerProfessor);
    }

    @GetMapping("{registerStudent}")
    public List<Classroom> getClassroomsByRegisterStudent(@PathVariable("registerStudent") Integer registerStudent) {
        return ClassroomService.getClassroomByRegisterStudent(registerStudent);
    }

}
