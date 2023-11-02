package com.chamada.macfi;

import com.chamada.macfi.controller.ProfessorController;
import com.chamada.macfi.repository.AlunoRepository;
import com.chamada.macfi.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MacfiApplication implements CommandLineRunner {

	@Autowired
	private ProfessorRepository professorRepository;

	@Autowired
	private AlunoRepository alunoRepository;

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
