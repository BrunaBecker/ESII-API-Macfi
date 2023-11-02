package com.chamada.macfi.model;

import com.chamada.macfi.model.person.Student;
import com.chamada.macfi.model.person.Professor;
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
    private Localization defaultLocation;

    @ManyToOne
    private Professor professor;
    @ManyToMany(mappedBy = "turmas")
    private List<Student> students;
    @OneToMany(mappedBy = "turma")
    private  List<Attendance> attendances;
}