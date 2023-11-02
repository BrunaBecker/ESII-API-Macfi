package com.chamada.macfi.model.person;

import com.chamada.macfi.model.Classroom;
import com.chamada.macfi.model.Localization;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@NoArgsConstructor
@Getter
@Setter
@Entity
public class Professor extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private RegisterProfessor siape;

    @OneToMany(mappedBy = "professor", cascade = CascadeType.PERSIST)
    private List<Localization> localization;

    @OneToMany(fetch = FetchType.EAGER,cascade=CascadeType.PERSIST)
    private List<Classroom> classrooms;



}
