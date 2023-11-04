package com.macfi.controller;

import com.macfi.model.Classroom;
import com.macfi.model.person.Student;
import com.macfi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }


    @PutMapping
    public Student updateStudent(@RequestBody Student student) {
        return studentService.updateStudent(student);
    }

    @PutMapping("{identifier}/class")
    public Student addClassroom(@RequestBody Classroom classroom, @PathVariable("identifier") String identifier) {
        Student Student = studentService.getStudentByIdentifier(identifier);
        Student.getClassrooms().add(classroom);
        return studentService.updateStudent(Student);
    }

    @GetMapping("/{idStudent}")
    public Student getStudentById(@PathVariable("idStudent") Long id) {
        return studentService.getStudentById(id);
    }

}