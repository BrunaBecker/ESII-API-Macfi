package com.macfi.controller;

import com.macfi.model.Classroom;
import com.macfi.model.person.Professor;
import com.macfi.modelMapper.modelMapping;
import com.macfi.payload.ClassroomDto;
import com.macfi.payload.ProfessorDto;
import com.macfi.service.ClassroomService;
import com.macfi.service.ProfessorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:8080")
@RestController
@RequestMapping("professor")
//@RequestMapping("Professors")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private ClassroomService classroomService;


    @GetMapping
    public ResponseEntity<List<ProfessorDto>> getProfessors() {
        return ResponseEntity.ok(professorService.getProfessors());
    }

    @Operation(
            summary = "Create Professor REST API",
            description = "Create Professor REST API is used to save post into database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )
    @PostMapping
    public ResponseEntity<ProfessorDto> createProfessor(@RequestBody ProfessorDto professor) {
        return new ResponseEntity<>(professorService.createProfessor(professor), HttpStatus.CREATED);
    }


    @PutMapping
    public ResponseEntity<ProfessorDto> updateProfessor(@RequestBody ProfessorDto professor) {
        return ResponseEntity.ok(professorService.updateProfessor(professor));
    }

    @PutMapping("{identifier}/class")
    public ResponseEntity<ProfessorDto> addClassroom(@RequestBody ClassroomDto classroom, @PathVariable("identifier") String identifier) {
        Professor professor = modelMapping.getInstance().mapToEntity(professorService.getProfessorByIdentifier(identifier), Professor.class);
        Classroom c = modelMapping.getInstance().mapToEntity(classroomService.getClassroomById(classroom.getId()), Classroom.class);

        professor.getClassrooms().add(c);
        return ResponseEntity.ok(modelMapping.getInstance().mapToEntity(professorService.updateProfessor(modelMapping.getInstance().mapToDto(professor, ProfessorDto.class)), ProfessorDto.class));
    }

    @GetMapping("{idProfessor}") //localhost:8080/professor/1
    public ResponseEntity<ProfessorDto> getProfessorById(@PathVariable("idProfessor") Long id) {
        return ResponseEntity.ok(professorService.getProfessorById(id));
    }

    @GetMapping("byIdentifier/{identifier}") //localhost:8080/professor/byIdentifier/1
    public ResponseEntity<ProfessorDto> getProfessorByIdentifier(@PathVariable("identifier") String identifier) {
        return ResponseEntity.ok(professorService.getProfessorByIdentifier(identifier));
    }

    @GetMapping("byClassroomCode/{code}") //localhost:8080/professor/byClassroomCode/1
    public ResponseEntity<ProfessorDto> getProfessorByClassroomCode(@PathVariable("code") String code) {
        return ResponseEntity.ok(professorService.getProfessorByClassroomCode(code));
    }


}
