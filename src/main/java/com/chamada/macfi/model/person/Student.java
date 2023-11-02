package com.chamada.macfi.model.person;

import com.chamada.macfi.model.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
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

    @OneToMany
    @JoinColumn(name = "atestadoId", referencedColumnName = "id")
    private List<Atestado> atestados;

    @OneToMany
    @JoinColumn(name = "chamadaId", referencedColumnName = "id")
    private List<Chamada> chamadas;

    public Aluno(String nome, String nomeSocial, String CPF, Date dataNascimento) {
        this.nome = nome;
        this.nomeSocial = nomeSocial;
        this.CPF = CPF;
        this.dataNascimento = dataNascimento;
    }

}
