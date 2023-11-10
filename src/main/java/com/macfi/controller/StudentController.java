package com.macfi.controller;

import com.macfi.model.Classroom;
import com.macfi.model.person.Student;
import com.macfi.service.ClassroomService;
import com.macfi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:8080")
@RestController
@RequestMapping("student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private ClassroomService classroomService;

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

    @PutMapping("{identifier}/class/{idClass}")
    public Student addClassroom(@PathVariable("idClass") Long idClass, @PathVariable("identifier") String identifier) {
        Student Student = studentService.getStudentByIdentifier(identifier);
        if (classroomService.getClassroomById(idClass) != null) {
            Student.getClassrooms().add(classroomService.getClassroomById(idClass));
            return studentService.updateStudent(Student);
        } else {
            return null;
        }

    }


    @GetMapping("/{idStudent}")
    public Student getStudentById(@PathVariable("idStudent") Long id) {
        return studentService.getStudentById(id);
    }

    @GetMapping("byIdentifier/{identifier}")
    public Student getStudentByIdentifier(@PathVariable("identifier") String identifier) {
        return studentService.getStudentByIdentifier(identifier);
    }

}