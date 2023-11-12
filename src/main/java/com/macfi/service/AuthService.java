package com.macfi.service;

import com.macfi.payload.ProfessorDto;
import com.macfi.payload.StudentDto;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private StudentService studentService;

    public Object loginPerson(String identifier, String password) {
        Object object = professorService.login(identifier, password);
        if (object == null) {
            object = studentService.login(identifier, password);
        }
        return object;
    }

    public ProfessorDto loginProfessor(String identifier, String password) {
        return professorService.login(identifier, password);
    }

    public StudentDto loginStudent(String identifier, String password) {
        return studentService.login(identifier, password);
    }


}
