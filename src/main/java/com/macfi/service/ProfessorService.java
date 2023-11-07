package com.macfi.service;

import com.macfi.exception.EntityNotFoundException;
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

    public Professor deleteProfessorByIdentifier(String identifier) {
        Professor aProfessor = getProfessorByIdentifier(identifier);
        professorRepository.deleteById(aProfessor.getId());
        return aProfessor;
    }

    public Professor deleteProfessor(Professor professor) {
        Professor aProfessor = getProfessorById(professor.getId());
        professorRepository.deleteById(aProfessor.getId());
        return aProfessor;
    }

    public Professor getProfessorById(Long id) {
        return (Professor) professorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("professor não encontrado"));
    }

    public Professor getProfessorByIdentifier(String identifier) {
        return professorRepository.findByIdentifier(identifier);
        //.orElseThrow(() -> new EntityNotFoundException());
    }

    public Professor updateProfessor(Professor professor) {
        Professor aProfessor = getProfessorById(professor.getId());
        if (!professor.getId().equals(aProfessor.getId())) {
            professorRepository.findById(professor.getId())
                    .orElseThrow(() -> new EntityNotFoundException("professor não encontrado"));
        }
        return professorRepository.save(professor);
    }

    public List<Professor> getProfessors() {
        return professorRepository.findAllByRepository();
    }

    public Professor getProfessorByClassroomCode(String code) {
        return professorRepository.findByClassroomCode(code);
    }
}
