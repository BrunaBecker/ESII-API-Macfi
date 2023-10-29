package com.chamada.macfi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Chamada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date Data;
//    private Turma Turma;
    private Time HoraInicio;
    private Time HoraTermino;
    private Time Duracao;
//    private Integer ID;

    @OneToMany
    private ArrayList<Aluno> alunos;
    
    @OneToOne
    private Turma turma;
}
