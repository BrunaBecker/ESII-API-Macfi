package com.macfi.service;

import com.macfi.exception.EntityNotFoundException;
import com.macfi.payload.ProfessorDto;
import com.macfi.payload.StudentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private StudentService studentService;

    public Object loginPerson(String identifier, String password) {

        try {
            return professorService.login(identifier, password);
        } catch (EntityNotFoundException e) {
            return studentService.login(identifier, password);
        }
    }

    public ProfessorDto loginProfessor(String identifier, String password) {
        return professorService.login(identifier, password);
    }

    public StudentDto loginStudent(String identifier, String password) {
        return studentService.login(identifier, password);
    }


}
