package com.macfi.service;

import com.macfi.exception.EntityNotFoundException;
import com.macfi.model.person.Student;
import com.macfi.repository.PersonRepository;
import com.macfi.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private PersonRepository<Student, Long> personRepository;

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

    public Student deleteStudentByIdentifier(String identifier) {
        Student aStudent = getStudentByIdentifier(identifier);
        studentRepository.deleteById(aStudent.getId());
        return aStudent;
    }

    public Student getStudentById(Long id) {
        return (Student) studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student não encontrado"));
    }

    public Student getStudentByIdentifier(String identifier) {
        return studentRepository.findByIdentifier(identifier);
    }

    public Student updateStudent(Student Student) {
        Student aStudent = getStudentById(Student.getId());
        if (!Student.getId().equals(aStudent.getId())) {
            studentRepository.findById(Student.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Student nao encontrado"));
        }
        return studentRepository.save(Student);
    }

    public List<Student> getStudents() {
        return studentRepository.findAllByRepository();
    }

    public List<Student> getStudentsTest() {
        return (List<Student>) (Object) studentRepository.findAll(Sort.by("id"));
    }
}
