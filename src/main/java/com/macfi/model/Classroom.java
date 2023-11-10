package com.macfi.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.macfi.model.person.Professor;
import com.macfi.model.person.Student;
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
@Table(name = "classroom")
@ToString
public class Classroom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String code;
    private String semester;


    @Temporal(TemporalType.TIME)
    private String startHour;

    @Temporal(TemporalType.TIME)
    private String endHour;


    @OneToOne(cascade = CascadeType.ALL, mappedBy = "classroom")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private Location defaultLocation;

    @ManyToOne
    private Professor professor;

    @ManyToMany(mappedBy = "classrooms")
    @ToString.Exclude
    private List<Student> students;

    @OneToMany(mappedBy = "classroom", cascade = CascadeType.ALL)
    @ToString.Exclude
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private List<Attendance> attendances;


    public Classroom(String name, String code, String semester, Location defaultLocation, Professor professor, List<Student> students, List<Attendance> attendances) {
        this.name = name;
        this.code = code;
        this.semester = semester;
        this.defaultLocation = defaultLocation;
        this.professor = professor;
        this.students = students;
        this.attendances = attendances;
    }
}