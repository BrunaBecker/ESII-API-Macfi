package com.macfi.service;

import com.macfi.exception.EntityNotFoundException;
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


    public Classroom getClassroomById(Long id) {
        return ClassroomRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Classroom não encontrada"));
    }

    public List<Classroom> getClassroomByProfessor(String identifier) {
        return ClassroomRepository.findByProfessor(identifier);
    }

    public List<Classroom> getClassroomByStudent(String identifier) {
        return ClassroomRepository.findByStudent(identifier);
    }

    public Classroom updateClassroom(Classroom Classroom) {
        Classroom aClassroom = getClassroomById(Classroom.getId());
        if (!Classroom.getId().equals(aClassroom.getId())) {
            ClassroomRepository.findById(Classroom.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Classroom não encontrada"));
        }
        return ClassroomRepository.save(Classroom);
    }

    public List<Classroom> getClassrooms() {
        return ClassroomRepository.findAll(Sort.by("id"));
    }

}
