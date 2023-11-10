package com.macfi.service;

import com.macfi.exception.EntityNotFoundException;
import com.macfi.model.person.Student;
import com.macfi.modelMapper.modelMapping;
import com.macfi.payload.StudentDto;
import com.macfi.repository.PersonRepository;
import com.macfi.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private PersonRepository<Student, Long> personRepository;

    public StudentDto createStudent(StudentDto studentDto) {
        return modelMapping.getInstance().mapToDto(studentRepository.save(modelMapping.getInstance().mapToEntity(studentDto, Student.class)), StudentDto.class);
    }



    public StudentDto getStudentById(Long id) {
        return modelMapping.getInstance().mapToDto(studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student not found")), StudentDto.class);
    }

    public StudentDto getStudentByIdentifier(String identifier) {
        return modelMapping.getInstance().mapToDto(studentRepository.findByIdentifier(identifier), StudentDto.class);
    }

    public StudentDto updateStudent(StudentDto studentDto) {
        Student aStudent = modelMapping.getInstance().mapToEntity(getStudentById(studentDto.getId()), Student.class);
        if (!studentDto.getId().equals(aStudent.getId())) {
            studentRepository.findById(studentDto.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Student not found"));
        }
        return modelMapping.getInstance().mapToDto(studentRepository.save(aStudent), StudentDto.class);
    }

    public List<StudentDto> getStudents() {
        List<Student> students = studentRepository.findAllByRepository();
        return students.stream().map(student -> modelMapping.getInstance().mapToDto(student, StudentDto.class)).collect(Collectors.toList());
    }

    public List<StudentDto> getStudentsByClassroom(Long id) {
        List<Student> students = studentRepository.findByClassroomId(id);
        return students.stream().map(student -> modelMapping.getInstance().mapToDto(student, StudentDto.class)).collect(Collectors.toList());
    }

    public StudentDto getStudentByWaiver(Long idWaiver) {
        return modelMapping.getInstance().mapToDto(studentRepository.findAllByWaiverId(idWaiver), StudentDto.class);
    }

    public List<StudentDto> getStudentsByAttendance(Long idAttendance) {
        List<Student> students = studentRepository.findAllByAttendanceId(idAttendance);
        return students.stream().map(student -> modelMapping.getInstance().mapToDto(student, StudentDto.class)).collect(Collectors.toList());
    }

    public List<StudentDto> getStudentsByAttendanceHappening(Long idAttendance) {
        List<Student> students = studentRepository.findAllByAttendanceHappeningId(idAttendance);
        return students.stream().map(student -> modelMapping.getInstance().mapToDto(student, StudentDto.class)).collect(Collectors.toList());
    }

    public StudentDto getStudentByEmail(String email) {
        return modelMapping.getInstance().mapToDto(studentRepository.findByEmail(email), StudentDto.class);
    }

    public List<StudentDto> getStudentsByClassroomCode(String code) {
        List<Student> students = studentRepository.findAllByClassroomCode(code);
        return students.stream().map(student -> modelMapping.getInstance().mapToDto(student, StudentDto.class)).collect(Collectors.toList());
    }
}
