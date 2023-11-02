package com.chamada.macfi.model;

import com.chamada.macfi.model.person.Student;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "atestado")
public class Atestado {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private String arquivo;

    private String descricao;

    private Date dataEnvio;

    private Date dataAceito;

    private boolean estado;

    private String comentario;

    @ManyToOne
    @JoinColumn(name = "aluno_id", referencedColumnName = "id")
    private Student student;


}
