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
@RequestMapping("Professors")
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

    @DeleteMapping("{idProfessor}")
    public void deleteProfessor(@PathVariable("idProfessor") Long id) {
        //TODO: clean all the related parameters
        professorService.deleteProfessor(id);
    }


    @PutMapping
    public Professor updateProfessor(@RequestBody Professor professor) {
        return professorService.updateProfessor(professor);
    }

    @PutMapping("{registerProfessor}")
    public Professor adicionarClassroom(@RequestBody Classroom Classroom, @PathVariable("registerProfessor") Integer registerProfessor) {
        Professor professor = professorService.getProfessorByregisterProfessor(registerProfessor);
        professor.getClassrooms().add(Classroom);
        return professorService.updateProfessor(professor);
    }

    @GetMapping("{idProfessor}")
    public Professor getProfessorById(@PathVariable("idProfessor") Long id) {
        return professorService.getProfessorById(id);
    }

    @GetMapping("/{registerProfessor}")
    public Professor getProfessorByregisterProfessor(@PathVariable("registerProfessor") Integer registerProfessor) {
        return professorService.getProfessorByregisterProfessor(registerProfessor);
    }

    @GetMapping("/{registerProfessor}/Classrooms")
    public List<Classroom> getClassroomsByregisterProfessor(@PathVariable("registerProfessor") Integer registerProfessor) {
        return professorService.getClassroomsByregisterProfessor(registerProfessor);
    }

    @GetMapping("/{registerProfessor}/Location")
    public Location getLocationByregisterProfessor(@PathVariable("registerProfessor") Integer registerProfessor) {
        return professorService.getLocationByregisterProfessor(registerProfessor);
    }

}
