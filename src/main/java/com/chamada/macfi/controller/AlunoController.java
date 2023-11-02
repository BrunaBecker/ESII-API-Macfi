package com.chamada.macfi.controller;

import com.chamada.macfi.model.person.Aluno;
import com.chamada.macfi.model.Turma;
import com.chamada.macfi.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("alunos")
public class AlunoController {
    @Autowired
    private AlunoService alunoService;

    @GetMapping
    public List<Aluno> recuperaAlunos(){ return alunoService.recuperaAlunos(); }

    @PostMapping
    public Aluno cadastrarAluno(@RequestBody Aluno aluno) { return alunoService.cadastraAluno(aluno); }

    @DeleteMapping("{idAluno}")
    public void removerAluno(@PathVariable Long id){
        alunoService.removerAluno(id);
    }

    @PutMapping
    public Aluno atualizarAluno(@RequestBody Aluno aluno) { return alunoService.atualizaAluno(aluno); }

    @PutMapping("{matricula}")
    public Aluno adicionarTurma(@RequestBody Turma turma, @PathVariable("matricula") Integer matricula) {
        Aluno aluno = alunoService.recuperaAlunoPorMatricula(matricula);
        aluno.getTurmas().add(turma);
        return alunoService.atualizaAluno(aluno);
    }

    @GetMapping("/{idAluno}")
    public Aluno recuperaAlunoPorId(@PathVariable("isAluno") Long id) {
        return alunoService.recuperaAlunoPorId(id);
    }

//    @GetMapping("/{matricula}")
//    public Aluno recuperaAlunoPorMatricula(@PathVariable("matricula") Integer matricula){
//        return alunoService.recuperaAlunoPorMatricula(matricula);
//    }


}