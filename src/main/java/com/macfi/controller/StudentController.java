package com.macfi.controller;

import com.macfi.model.Classroom;
import com.macfi.model.person.Student;
import com.macfi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @PostMapping
    public Student createStudent(@RequestBody Student Student) {
        return studentService.createStudent(Student);
    }

    @DeleteMapping("{idStudent}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }

    @PutMapping
    public Student updateStudent(@RequestBody Student Student) {
        return studentService.updateStudent(Student);
    }

    @PutMapping("{registerStudent}")
    public Student adicionarClassroom(@RequestBody Classroom Classroom, @PathVariable("registerStudent") Integer registerStudent) {
        Student Student = studentService.getStudentByRegisterStudent(registerStudent);
        Student.getClassrooms().add(Classroom);
        return studentService.updateStudent(Student);
    }

    @GetMapping("/{idStudent}")
    public Student getStudentById(@PathVariable("isStudent") Long id) {
        return studentService.getStudentById(id);
    }

//    @GetMapping("/{registerStudent}")
//    public Student getStudentByRegisterStudent(@PathVariable("registerStudent") Integer registerStudent){
//        return StudentService.getStudentByRegisterStudent(registerStudent);
//    }


}