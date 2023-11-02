package com.macfi.service;

import com.macfi.exception.EntidadeNaoEncontradaException;
import com.macfi.model.Classroom;
import com.macfi.model.Location;
import com.macfi.model.person.Professor;
import com.macfi.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    public Professor createProfessor(Professor professor) {
        return professorRepository.save(professor);
    }

    public void deleteProfessor(Long id) {
        getProfessorById(id);
        professorRepository.deleteById(id);
    }

    public Professor deleteProfessor(Professor professor) {
        Professor aProfessor = getProfessorById(professor.getId());
        professorRepository.deleteById(aProfessor.getId());
        return aProfessor;
    }

    public Professor getProfessorById(Long id) {
        return professorRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("professor não encontrado"));
    }

    public Professor getProfessorByregisterProfessor(Integer registerProfessor) {
        return professorRepository.findByregisterProfessor(registerProfessor);
        //.orElseThrow(() -> new EntidadeNaoEncontradaException());
    }

    public List<Classroom> getClassroomsByregisterProfessor(Integer registerProfessor) {
        return professorRepository.findClassroomsByregisterProfessor(registerProfessor);
    }

    public Location getLocationByregisterProfessor(Integer registerProfessor) {
        return professorRepository.findLocationByregisterProfessor(registerProfessor);
    }

    public Professor updateProfessor(Professor professor) {
        Professor aProfessor = getProfessorById(professor.getId());
        if (!professor.getId().equals(aProfessor.getId())) {
            professorRepository.findById(professor.getId())
                    .orElseThrow(() -> new EntidadeNaoEncontradaException("professor não encontrado"));
        }
        return professorRepository.save(professor);
    }

    public List<Professor> getProfessors() {
        return professorRepository.findAll(Sort.by("id"));
    }

}
