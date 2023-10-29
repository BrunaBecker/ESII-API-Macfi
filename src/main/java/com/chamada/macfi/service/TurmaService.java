package com.chamada.macfi.service;

import com.chamada.macfi.exception.EntidadeNaoEncontradaException;
import com.chamada.macfi.model.Turma;
import com.chamada.macfi.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurmaService {

    @Autowired
    TurmaRepository turmaRepository;

    public Turma cadastraTurma(Turma turma) { return turmaRepository.save(turma); }

    public void removerTurma(Long id){
        recuperaTurmaPorId(id);
        turmaRepository.deleteById(id);
    }

    public Turma recuperaTurmaPorId(Long id){
        return turmaRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Turma não encontrada"));
    }

    public List<Turma> recuperaTurmaPorSIAPE(Integer SIAPE){
        return turmaRepository.findBySIAPE(SIAPE);
    }

    public List<Turma> recuperaTurmaPorMatricula(Integer matricula) {
        return turmaRepository.findByMatricula(matricula);
    }

    public Turma atualizaTurma(Turma turma){
        Turma umaTurma = recuperaTurmaPorId(turma.getId());
        if (!turma.getId().equals(umaTurma.getId())){
            turmaRepository.findById(turma.getId())
                    .orElseThrow(() -> new EntidadeNaoEncontradaException("Turma não encontrada"));
        }
        return turmaRepository.save(turma);
    }

    public List<Turma> recuperaTurmas() { return turmaRepository.findAll(Sort.by("id")); }

}
