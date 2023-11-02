package com.chamada.macfi.model;

import com.chamada.macfi.model.utils.StudentAtAttendanceState;
import com.chamada.macfi.model.person.Student;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class EstadoChamada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private StudentAtAttendanceState estadoAluno;

    private boolean studentHasResponded;

    private boolean validated;

    @OneToOne
    private Student student;

    @ManyToOne
    private Chamada chamada;

    @OneToMany(mappedBy = "estadoChamada")
    private List<Ping> pingsSuccess;

    @OneToMany(mappedBy = "estadoChamada")
    private List<Ping> pingsFail;

}
