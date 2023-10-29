package com.chamada.macfi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer codigo;
    private Date dataInicio;
    private Date dataTermino;
    private boolean isActive;

    @OneToOne(mappedBy = "matricula")
    private Aluno Aluno;
}
