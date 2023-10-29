package com.chamada.macfi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
//@ToString
public class Aluno extends Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "matriculaId", referencedColumnName = "id")
    private Matricula matricula;

    @ManyToMany
    @JoinTable(name = "aluno_turma", joinColumns = @JoinColumn(name = "alunoId", referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name = "turmaId", referencedColumnName = "id"))
    private List<Turma> turmas;

    public Aluno(String nome, String endereco, String genero,
                 String nomeSocial, Integer CPF, Date dataNascimento) {
        this.nome = nome;
        this.endereco = endereco;
        this.genero = genero;
        this.nomeSocial = nomeSocial;
        this.CPF = CPF;
        this.dataNascimento = dataNascimento;
    }

}
