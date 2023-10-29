package com.chamada.macfi.service;

import com.chamada.macfi.exception.EntidadeNaoEncontradaException;
import com.chamada.macfi.model.Localizacao;
import com.chamada.macfi.model.Professor;
import com.chamada.macfi.model.Turma;
import com.chamada.macfi.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    public Professor cadastraProfessor(Professor professor) { return professorRepository.save(professor); }

    public void removerProfessor(Long id){
        recuperaProfessorPorId(id);
        professorRepository.deleteById(id);
    }

    public Professor removerProfessor(Professor professor) {
        Professor umProfessor = recuperaProfessorPorId(professor.getId());
        professorRepository.deleteById(umProfessor.getId());
        return umProfessor;
    }

    public Professor recuperaProfessorPorId(Long id){
        return professorRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("professor não encontrado"));
    }

    public Professor recuperaProfessorPorSIAPE(Integer SIAPE){
        return professorRepository.findBySIAPE(SIAPE);
                //.orElseThrow(() -> new EntidadeNaoEncontradaException());
    }

    public List<Turma> recuperaTurmasPorSIAPE(Integer SIAPE){
        return professorRepository.findTurmasBySIAPE(SIAPE);
    }

    public Localizacao recuperaLocalizacaoPorSIAPE(Integer SIAPE){
        return professorRepository.findLocalizacaoBySIAPE(SIAPE);
    }

    public Professor atualizaProfessor(Professor professor){
        Professor umProfessor = recuperaProfessorPorId(professor.getId());
        if (!professor.getId().equals(umProfessor.getId())){
            professorRepository.findById(professor.getId())
                    .orElseThrow(() -> new EntidadeNaoEncontradaException("professor não encontrado"));
        }
        return professorRepository.save(professor);
    }

    public List<Professor> recuperaProfessores() { return professorRepository.findAll(Sort.by("id")); }

}
