package com.chamada.macfi.controller;

import com.chamada.macfi.model.person.Professor;
import com.chamada.macfi.model.Turma;
import com.chamada.macfi.service.AlunoService;
import com.chamada.macfi.service.ProfessorService;
import com.chamada.macfi.service.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("turmas")
public class TurmaController {

    @Autowired
    TurmaService turmaService;

    @Autowired
    ProfessorService professorService;

    @Autowired
    AlunoService alunoService;

    @GetMapping
    public List<Turma> recuperaTurmas(){ return turmaService.recuperaTurmas(); }

    @PostMapping
    public Turma cadastraTurma(@RequestBody Turma turma) { return turmaService.cadastraTurma(turma); }

    @DeleteMapping("{idTurma}")
    public void remnoverTurma(@PathVariable("idTurma") Long id){
        //TODO: clean all the related parameters
        turmaService.removerTurma(id);
    }


    @PutMapping
    public Turma atualizarTurma(@RequestBody Turma turma) { return turmaService.atualizaTurma(turma); }

    @PutMapping("{siape}")
    public Professor adicionarTurma(@RequestBody Turma turma, @PathVariable Integer SIAPE) {
        Professor professor = professorService.recuperaProfessorPorSIAPE(SIAPE);
        professor.getTurmas().add(turma);
        return professorService.atualizaProfessor(professor);
    }

//    @PutMapping("{matricula}")
//    public Aluno adicionarTurma(@RequestBody Turma turma, @PathVariable("matricula") Integer matricula) {
//        Aluno aluno = alunoService.recuperaAlunoPorMatricula(matricula);
//        aluno.getTurmas().add(turma);
//        return alunoService.atualizaAluno(aluno);
//    }

    @GetMapping("{siape}")
    public List<Turma> recuperaTurmasPorSIAPE(@PathVariable("siape") Integer siape){
        return turmaService.recuperaTurmaPorSIAPE(siape);
    }

    @GetMapping("{matricula}")
    public List<Turma> recuperaTurmasPorMatricula(@PathVariable("matricula") Integer matricula){
        return turmaService.recuperaTurmaPorMatricula(matricula);
    }

}
