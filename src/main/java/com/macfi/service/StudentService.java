package com.macfi.service;

import com.macfi.exception.EntidadeNaoEncontradaException;
import com.macfi.model.Classroom;
import com.macfi.model.person.Student;
import com.macfi.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        getStudentById(id);
        studentRepository.deleteById(id);
    }

    public Student deleteStudent(Student student) {
        Student aStudent = getStudentById(student.getId());
        studentRepository.deleteById(aStudent.getId());
        return aStudent;
    }

    public List<Classroom> getClassroomsByRegisterStudent(Integer registerStudent) {
        return studentRepository.findClassroomsByRegisterStudent(registerStudent);
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Student não encontrado"));
    }

    public Student getStudentByRegisterStudent(Integer registrationId) {
        return studentRepository.findByRegisterStudent(registrationId);
    }

    public Student updateStudent(Student Student) {
        Student umStudent = getStudentById(Student.getId());
        if (!Student.getId().equals(umStudent.getId())) {
            studentRepository.findById(Student.getId())
                    .orElseThrow(() -> new EntidadeNaoEncontradaException("Student nao encontrado"));
        }
        return studentRepository.save(Student);
    }

    public List<Student> getStudents() {
        return studentRepository.findAll(Sort.by("id"));
    }
}
