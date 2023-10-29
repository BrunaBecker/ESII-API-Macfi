package com.chamada.macfi;

import com.chamada.macfi.controller.ProfessorController;
import com.chamada.macfi.model.Professor;
import com.chamada.macfi.model.Turma;
import com.chamada.macfi.repository.AlunoRepository;
import com.chamada.macfi.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

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

		Professor professor = new Professor(
				"professor", "seila", "prof", "prof", 1234567890, new Date(), "email@ic.uff.br",
				"senha", 123456789, new ArrayList<>()
		);
		professorRepository.save(professor);

		Professor resposta = professorController.recuperaProfessorPorId(1L);

		Turma turma = new Turma();

		professorController.adicionarTurma(turma, 123456789);

		System.out.println(professorController.recuperaProfessorPorId(1L));


	}


}
