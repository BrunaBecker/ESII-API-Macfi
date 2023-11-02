package com.chamada.macfi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

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
    private String supportingText;

    private Time HoraInicio;
    private Time HoraTermino;

    private Time Duracao;
    private boolean isAutomatic;
    private boolean isActive;

    @OneToOne
    private VirtualZone virtualZone;

    @ManyToOne
    private Classroom classroom;

    @OneToMany(mappedBy = "chamada")
    private List<EstadoChamada> StatusStudentAttendance;

}
