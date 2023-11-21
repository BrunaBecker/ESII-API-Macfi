package com.macfi.service;

import com.macfi.exception.EntityNotFoundException;
import com.macfi.model.Attendance;
import com.macfi.model.Classroom;
import com.macfi.model.Event;
import com.macfi.model.Location;
import com.macfi.model.person.Student;
import com.macfi.modelMapper.modelMapping;
import com.macfi.payload.AttendanceDto;
import com.macfi.payload.ClassroomDto;
import com.macfi.payload.EventDto;
import com.macfi.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClassroomService {

    @Autowired
    private ClassroomRepository ClassroomRepository;


    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private AttendanceRepository attendanceRepository;

    public ClassroomDto createClassroom(ClassroomDto classroomDto) {
        Classroom c = modelMapping.getInstance().mapToEntity(classroomDto, Classroom.class);
        return modelMapping.getInstance().mapToDto(ClassroomRepository.save(c), ClassroomDto.class);
    }


    public ClassroomDto getClassroomById(Long id) {
        return modelMapping.getInstance().mapToDto(ClassroomRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Classroom not found")), ClassroomDto.class);
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
        ClassroomRepository.findById(classroomDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Classroom not found"));
        Classroom c = modelMapping.getInstance().mapToEntity(classroomDto, Classroom.class);
        return modelMapping.getInstance().mapToDto(ClassroomRepository.save(c), ClassroomDto.class);
    }

    public List<ClassroomDto> getClassroomByCode(String code) {
        List<Classroom> classrooms = ClassroomRepository.findByCode(code);
        return classrooms.stream().map(classroom -> modelMapping.getInstance().mapToDto(classroom, ClassroomDto.class)).collect(Collectors.toList());

    }

    public List<ClassroomDto> getClassrooms() {
        List<Classroom> classrooms = ClassroomRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        return classrooms.stream().map(classroom -> modelMapping.getInstance().mapToDto(classroom, ClassroomDto.class)).collect(Collectors.toList());
    }


    public ClassroomDto setEvent(Long idClassroom, Long idEvent) {
        Event event = eventRepository.findById(idEvent).orElseThrow(() -> new EntityNotFoundException("Event not found"));
        Classroom classroom = ClassroomRepository.findById(idClassroom).orElseThrow(() -> new EntityNotFoundException("Classroom not found"));
        classroom.getEvents().add(event);
        return modelMapping.getInstance().mapToDto(ClassroomRepository.save(classroom), ClassroomDto.class);
    }

    public ClassroomDto removeStudent(Long idClassroom, Long idStudent) {
        Student student = studentRepository.findById(idStudent).orElseThrow(() -> new EntityNotFoundException("Student not found"));
        Classroom classroom = ClassroomRepository.findById(idClassroom).orElseThrow(() -> new EntityNotFoundException("Classroom not found"));
        classroom.getStudents().remove(student);
        return modelMapping.getInstance().mapToDto(ClassroomRepository.save(classroom), ClassroomDto.class);
    }

    public ClassroomDto setStudent(Long idClassroom, Long idStudent) {
        Student student = studentRepository.findById(idStudent).orElseThrow(() -> new EntityNotFoundException("Student not found"));
        Classroom classroom = ClassroomRepository.findById(idClassroom).orElseThrow(() -> new EntityNotFoundException("Classroom not found"));
        classroom.getStudents().add(student);
        return modelMapping.getInstance().mapToDto(ClassroomRepository.save(classroom), ClassroomDto.class);
    }

    public ClassroomDto removeEvent(Long idClassroom, Long idEvent) {
        Event event = eventRepository.findById(idEvent).orElseThrow(() -> new EntityNotFoundException("Event not found"));
        Classroom classroom = ClassroomRepository.findById(idClassroom).orElseThrow(() -> new EntityNotFoundException("Classroom not found"));
        classroom.getEvents().remove(event);
        return modelMapping.getInstance().mapToDto(ClassroomRepository.save(classroom), ClassroomDto.class);
    }

    public ClassroomDto setLocation(Long idClassroom, Long idLocation) {
        Location location = locationRepository.findById(idLocation).orElseThrow(() -> new EntityNotFoundException("Location not found"));
        Classroom classroom = ClassroomRepository.findById(idClassroom).orElseThrow(() -> new EntityNotFoundException("Classroom not found"));
        classroom.setDefaultLocation(location);
        return modelMapping.getInstance().mapToDto(ClassroomRepository.save(classroom), ClassroomDto.class);
    }

    public ClassroomDto setAttendance(Long idClassroom, Long idAttendance) {
        Attendance attendance = attendanceRepository.findById(idAttendance).orElseThrow(() -> new EntityNotFoundException("Attendance not found"));
        Classroom classroom = ClassroomRepository.findById(idClassroom).orElseThrow(() -> new EntityNotFoundException("Classroom not found"));
        classroom.getAttendances().add(attendance);
        return modelMapping.getInstance().mapToDto(ClassroomRepository.save(classroom), ClassroomDto.class);
    }

    public ClassroomDto addAttendance(Long idClassroom, AttendanceDto attendanceDto) {
        Attendance attendance;
        try {
            attendance = attendanceRepository.findById(attendanceDto.getId()).orElseThrow(() -> new EntityNotFoundException("Attendance not found"));
        } catch (EntityNotFoundException e) {
            attendance = modelMapping.getInstance().mapToEntity(attendanceDto, Attendance.class);
        }
        Classroom classroom = ClassroomRepository.findById(idClassroom).orElseThrow(() -> new EntityNotFoundException("Classroom not found"));
        classroom.getAttendances().add(attendance);
        return modelMapping.getInstance().mapToDto(ClassroomRepository.save(classroom), ClassroomDto.class);
    }

    public ClassroomDto addEvent(Long idClassroom, EventDto eventDto) {
        Event event;
        try {
            event = eventRepository.findById(eventDto.getId()).orElseThrow(() -> new EntityNotFoundException("Event not found"));
        } catch (EntityNotFoundException e) {
            event = modelMapping.getInstance().mapToEntity(eventDto, Event.class);
        }
        Classroom classroom = ClassroomRepository.findById(idClassroom).orElseThrow(() -> new EntityNotFoundException("Classroom not found"));
        classroom.getEvents().add(event);
        return modelMapping.getInstance().mapToDto(ClassroomRepository.save(classroom), ClassroomDto.class);
    }
}
