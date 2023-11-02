package com.macfi;

import com.macfi.controller.ProfessorController;
import com.macfi.repository.ProfessorRepository;
import com.macfi.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MacfiApplication implements CommandLineRunner {

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ProfessorController professorController;

    public static void main(String[] args) {
        SpringApplication.run(MacfiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //build initial bd here

    }


}
