package com.macfi.model;

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
@ToString
public class Classroom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String code;
    private String semester;

    @OneToOne
    private Location defaultLocation;

    @ManyToOne
    private Professor professor;
    @ManyToMany(mappedBy = "classrooms")
    private List<Student> students;
    @OneToMany(mappedBy = "classroom")
    private List<Attendance> attendances;
}