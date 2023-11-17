package com.macfi.service;

import com.macfi.exception.EntityNotFoundException;
import com.macfi.model.person.Professor;
import com.macfi.modelMapper.modelMapping;
import com.macfi.payload.ProfessorDto;
import com.macfi.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    public ProfessorDto createProfessor(ProfessorDto professor) {
        return modelMapping.getInstance().mapToDto(professorRepository.save(modelMapping.getInstance().mapToEntity(professor, Professor.class)), ProfessorDto.class);
    }

    public ProfessorDto getProfessorById(Long id) {
        return modelMapping.getInstance().mapToDto(professorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Professor not found")), ProfessorDto.class);
    }

    public ProfessorDto getProfessorByIdentifier(String identifier) {
        Professor p = professorRepository.findByIdentifier(identifier);
        if (p == null) {
            throw new EntityNotFoundException("Professor not found");
        }
        return modelMapping.getInstance().mapToDto(p, ProfessorDto.class);
    }

    public ProfessorDto updateProfessor(ProfessorDto professor) {
        Professor aProfessor = modelMapping.getInstance().mapToEntity(getProfessorById(professor.getId()), Professor.class);
        if (!professor.getId().equals(aProfessor.getId())) {
            professorRepository.findById(professor.getId())
                    .orElseThrow(() -> new EntityNotFoundException("professor não encontrado"));
        }
        return modelMapping.getInstance().mapToDto(professorRepository.save(aProfessor), ProfessorDto.class);
    }

    public List<ProfessorDto> getProfessors() {
        List<Professor> professors = professorRepository.findAllByRepository();
        return professors.stream().map(professor -> modelMapping.getInstance().mapToDto(professor, ProfessorDto.class)).collect(Collectors.toList());
    }

    public ProfessorDto getProfessorByClassroomCode(String code) {
        return modelMapping.getInstance().mapToDto(professorRepository.findByClassroomCode(code), ProfessorDto.class);
    }

    public ProfessorDto login(String identifier, String password) {

        ProfessorDto professorDto = getProfessorByIdentifier(identifier);
        if (professorDto.getPassword().equals(password) && professorDto.getIsActive()) {
            return professorDto;
        } else {
            return null;
        }


    }
}
