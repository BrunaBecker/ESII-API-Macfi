package com.chamada.macfi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@NoArgsConstructor
@Getter
@Setter
@Entity
public class Professor extends Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer SIAPE;

    @OneToMany(mappedBy = "professor", cascade = CascadeType.PERSIST)
    private Localizacao localizacao;

    @OneToMany( fetch = FetchType.EAGER,cascade=CascadeType.PERSIST)
    private List<Turma> turmas;

    public Professor(String nome,
                 String nomeSocial, String CPF, Date dataNascimento,
                String email, String senha, Integer SIAPE, ArrayList<Turma> turmas) {
        this.nome = nome;
        this.nomeSocial = nomeSocial;
        this.CPF = CPF;
        this.dataNascimento = dataNascimento;
        this.SIAPE = SIAPE;
        this.email = email;
        this.senha = senha;
        this.turmas = turmas;
    }

}
