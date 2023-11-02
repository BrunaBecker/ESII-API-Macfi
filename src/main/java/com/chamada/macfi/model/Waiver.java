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
public class Waiver {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private String file;

    private String description;

    private Date sendDate;

    private Date acceptionDate;

    private boolean state;

    private String comment;

    @ManyToOne
    @JoinColumn(name = "aluno_id", referencedColumnName = "id")
    private Student student;


}
