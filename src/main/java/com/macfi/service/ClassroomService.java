package com.macfi.service;

import com.macfi.exception.EntidadeNaoEncontradaException;
import com.macfi.model.Classroom;
import com.macfi.repository.ClassroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassroomService {

    @Autowired
    ClassroomRepository ClassroomRepository;

    public Classroom createClassroom(Classroom Classroom) {
        return ClassroomRepository.save(Classroom);
    }

    public void deleteClassroom(Long id) {
        getClassroomById(id);
        ClassroomRepository.deleteById(id);
    }

    public Classroom getClassroomById(Long id) {
        return ClassroomRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Classroom não encontrada"));
    }

    public List<Classroom> getClassroomByregisterProfessor(Integer registerProfessor) {
        return ClassroomRepository.findByregisterProfessor(registerProfessor);
    }

    public List<Classroom> getClassroomByRegisterStudent(Integer registerStudent) {
        return ClassroomRepository.findByRegisterStudent(registerStudent);
    }

    public Classroom updateClassroom(Classroom Classroom) {
        Classroom aClassroom = getClassroomById(Classroom.getId());
        if (!Classroom.getId().equals(aClassroom.getId())) {
            ClassroomRepository.findById(Classroom.getId())
                    .orElseThrow(() -> new EntidadeNaoEncontradaException("Classroom não encontrada"));
        }
        return ClassroomRepository.save(Classroom);
    }

    public List<Classroom> getClassrooms() {
        return ClassroomRepository.findAll(Sort.by("id"));
    }

}
