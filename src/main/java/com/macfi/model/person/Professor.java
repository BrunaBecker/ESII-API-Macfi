package com.macfi.model.person;

import com.macfi.model.Classroom;
import com.macfi.model.Location;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@NoArgsConstructor
@Getter
@Setter
@Entity
public class Professor extends Person {

    @OneToOne
    private RegisterProfessor registerProfessor;

    @OneToMany(mappedBy = "professor", cascade = CascadeType.PERSIST)
    private List<Location> locations;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<Classroom> classrooms;


}
