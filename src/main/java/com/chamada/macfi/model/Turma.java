package com.chamada.macfi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Turma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer codigo;
//    private Curso Curso;
    private String semestre;

    @ManyToOne
    private Professor professor;

//    @ManyToMany(mappedBy = "turmas")
//    private List<Aluno> alunos;
}