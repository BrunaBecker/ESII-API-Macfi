package com.macfi.controller;

import com.macfi.model.Classroom;
import com.macfi.model.Location;
import com.macfi.model.person.Professor;
import com.macfi.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("professor")
//@RequestMapping("Professors")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @GetMapping
    public List<Professor> getProfessors() {
        return professorService.getProfessors();
    }

    @PostMapping
    public Professor createProfessor(@RequestBody Professor professor) {
        return professorService.createProfessor(professor);
    }


    @PutMapping
    public Professor updateProfessor(@RequestBody Professor professor) {
        return professorService.updateProfessor(professor);
    }

    @PutMapping("{identifier}/class")
    public Professor addClassroom(@RequestBody Classroom classroom, @PathVariable("identifier") String identifier) {
        Professor professor = professorService.getProfessorByIdentifier(identifier);
        professor.getClassrooms().add(classroom);
        return professorService.updateProfessor(professor);
    }

    @GetMapping("{idProfessor}") //localhost:5173/professor/1
    public Professor getProfessorById(@PathVariable("idProfessor") Long id) {
        return professorService.getProfessorById(id);
    }
    //TODO review this method
    @GetMapping("byIdentifier/{identifier}") //localhost:5173/professor/byIdentifier/1
    public Professor getProfessorByIdentifier(@PathVariable("identifier") String identifier) {
        return professorService.getProfessorByIdentifier(identifier);
    }


}
