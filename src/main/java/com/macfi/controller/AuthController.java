package com.macfi.controller;

import com.macfi.exception.EntityNotFoundException;
import com.macfi.exception.UserUnauthorized;
import com.macfi.payload.ProfessorDto;
import com.macfi.payload.StudentDto;
import com.macfi.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private AuthService authService;


    @GetMapping("login")
    public ResponseEntity<Object> login(@RequestParam("identifier") String identifier, @RequestParam("password") String password) {
        Object object;

        try {
            object = authService.loginPerson(identifier, password);
            return ResponseEntity.ok(object);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }


    @GetMapping("login/professor")
    public ResponseEntity<ProfessorDto> loginProfessor(@RequestParam("identifier") String identifier, @RequestParam("password") String password) {
        ProfessorDto professorDto;

        try {
            professorDto = authService.loginProfessor(identifier, password);
            return ResponseEntity.ok(professorDto);
        } catch (EntityNotFoundException | UserUnauthorized ae){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }

    }

    @GetMapping("login/student")
    public ResponseEntity<StudentDto> loginStudent(@RequestParam("identifier") String identifier, @RequestParam("password") String password) {
      StudentDto  studentDto;
        try {
            studentDto = authService.loginStudent(identifier, password);
            return ResponseEntity.ok(studentDto);
        } catch (EntityNotFoundException | UserUnauthorized ae){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
