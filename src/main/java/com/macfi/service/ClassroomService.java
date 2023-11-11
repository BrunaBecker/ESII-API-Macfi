package com.macfi.service;

import com.macfi.exception.EntityNotFoundException;
import com.macfi.model.Classroom;
import com.macfi.modelMapper.modelMapping;
import com.macfi.payload.ClassroomDto;
import com.macfi.repository.ClassroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClassroomService {

    @Autowired
    ClassroomRepository ClassroomRepository;

    public ClassroomDto createClassroom(ClassroomDto classroomDto) {
        Classroom c = modelMapping.getInstance().mapToEntity(classroomDto, Classroom.class);
        return modelMapping.getInstance().mapToDto(ClassroomRepository.save(c), ClassroomDto.class);
    }


    public ClassroomDto getClassroomById(Long id) {
        return modelMapping.getInstance().mapToDto(ClassroomRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Classroom não encontrada")), ClassroomDto.class);
    }

    public List<ClassroomDto> getClassroomByProfessor(String identifier) {
        List<Classroom> classrooms = ClassroomRepository.findByProfessor(identifier);
        return classrooms.stream().map(classroom -> modelMapping.getInstance().mapToDto(classroom, ClassroomDto.class)).collect(Collectors.toList());
    }

    public List<ClassroomDto> getClassroomByStudent(String identifier) {
        List<Classroom> classrooms = ClassroomRepository.findByStudent(identifier);
        return classrooms.stream().map(classroom -> modelMapping.getInstance().mapToDto(classroom, ClassroomDto.class)).collect(Collectors.toList());
    }

    public ClassroomDto updateClassroom(ClassroomDto classroomDto) {
        Classroom aClassroom = modelMapping.getInstance().mapToEntity(getClassroomById(classroomDto.getId()), Classroom.class);
        if (!classroomDto.getId().equals(aClassroom.getId())) {
            ClassroomRepository.findById(classroomDto.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Classroom não encontrada"));
        }
        return modelMapping.getInstance().mapToDto(ClassroomRepository.save(aClassroom), ClassroomDto.class);
    }

    public List<ClassroomDto> getClassrooms() {
        List<Classroom> classrooms = ClassroomRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        return classrooms.stream().map(classroom -> modelMapping.getInstance().mapToDto(classroom, ClassroomDto.class)).collect(Collectors.toList());
    }

}
