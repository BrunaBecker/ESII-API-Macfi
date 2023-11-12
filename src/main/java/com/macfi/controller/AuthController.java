package com.macfi.controller;

import com.macfi.payload.ProfessorDto;
import com.macfi.payload.StudentDto;
import com.macfi.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private AuthService authService;


    @GetMapping("login")
    public ResponseEntity<Object> login(@RequestParam("identifier") String identifier, @RequestParam("password") String password) {
        Object object = authService.loginPerson(identifier, password);

        if (object == null) {
            return ResponseEntity.badRequest().body("Login inválido");
        } else {
            return ResponseEntity.ok(object);
        }
    }


    @GetMapping("login/professor")
    public ResponseEntity<ProfessorDto> loginProfessor(@RequestParam("identifier") String identifier, @RequestParam("password") String password) {
        ProfessorDto professorDto = authService.loginProfessor(identifier, password);

        if (professorDto == null) {
            return ResponseEntity.badRequest().body(null);
        } else {
            return ResponseEntity.ok(professorDto);
        }
    }

    @GetMapping("login/student")
    public ResponseEntity<Object> loginStudent(@RequestParam("identifier") String identifier, @RequestParam("password") String password) {
        StudentDto studentDto = authService.loginStudent(identifier, password);

        if (studentDto == null) {
            return ResponseEntity.badRequest().body(null);
        } else {
            return ResponseEntity.ok(studentDto);
        }
    }
}
