package com.chamada.macfi.controller;

import com.chamada.macfi.model.Localizacao;
import com.chamada.macfi.model.Professor;
import com.chamada.macfi.model.Turma;
import com.chamada.macfi.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("professores")
//@RequestMapping("professores")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @GetMapping
    public List<Professor> recuperaProfessores() { return professorService.recuperaProfessores(); }

    @PostMapping
    public Professor cadastrarProfessor(@RequestBody Professor professor) { return professorService.cadastraProfessor(professor); }

    @DeleteMapping("{idProfessor}")
    public void removerProfessor(@PathVariable("idProfessor") Long id){
        //TODO: clean all the related parameters
        professorService.removerProfessor(id);
    }


    @PutMapping
    public Professor atualizarProfessor(@RequestBody Professor professor) { return professorService.atualizaProfessor(professor); }

    @PutMapping("{siape}")
    public Professor adicionarTurma(@RequestBody Turma turma, @PathVariable("siape") Integer siape) {
        Professor professor = professorService.recuperaProfessorPorSIAPE(siape);
        professor.getTurmas().add(turma);
        return professorService.atualizaProfessor(professor);
    }

    @GetMapping("{idProfessor}")
    public Professor recuperaProfessorPorId(@PathVariable("idProfessor") Long id){
        return professorService.recuperaProfessorPorId(id);
    }

    @GetMapping("/{siape}")
    public Professor recuperaProfessorPorSIAPE(@PathVariable("siape") Integer siape){
        return professorService.recuperaProfessorPorSIAPE(siape);
    }

    @GetMapping("/{siape}/turmas")
    public List<Turma> recuperaTurmasPorSIAPE(@PathVariable("siape") Integer siape){
        return professorService.recuperaTurmasPorSIAPE(siape);
    }

    @GetMapping("/{siape}/localizacao")
    public Localizacao recuperaLocalizacaoPorSIAPE(@PathVariable("siape") Integer siape){
        return professorService.recuperaLocalizacaoPorSIAPE(siape);
    }

}
