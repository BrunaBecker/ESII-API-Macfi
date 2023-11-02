package com.chamada.macfi.service;

import com.chamada.macfi.exception.EntidadeNaoEncontradaException;
import com.chamada.macfi.model.person.Aluno;
import com.chamada.macfi.model.Turma;
import com.chamada.macfi.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public Aluno cadastraAluno(Aluno aluno) { return alunoRepository.save(aluno); }

    public void removerAluno(Long id){
        recuperaAlunoPorId(id);
        alunoRepository.deleteById(id);
    }

    public Aluno removerAluno(Aluno aluno) {
        Aluno outroAluno = recuperaAlunoPorId(aluno.getId());
        alunoRepository.deleteById(outroAluno.getId());
        return outroAluno;
    }

    public List<Turma> recuperaTurmasPotMatricula(Integer matricula){
        return alunoRepository.findTurmasByMatricula(matricula);
    }

    public Aluno recuperaAlunoPorId(Long id) {
        return alunoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Aluno não encontrado"));
    }

    public Aluno recuperaAlunoPorMatricula(Integer matricula){
        return alunoRepository.findByMatricula(matricula);
    }

    public Aluno atualizaAluno(Aluno aluno){
        Aluno umAluno = recuperaAlunoPorId(aluno.getId());
        if (!aluno.getId().equals(umAluno.getId())){
            alunoRepository.findById(aluno.getId())
                    .orElseThrow(() -> new EntidadeNaoEncontradaException("aluno nao encontrado"));
        }
        return alunoRepository.save(aluno);
    }

    public List<Aluno> recuperaAlunos() {
        return alunoRepository.findAll(Sort.by("id"));
    }
}
