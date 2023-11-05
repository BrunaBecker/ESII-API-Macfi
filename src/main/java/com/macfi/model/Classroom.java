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
@Table(name = "classroom")
@ToString
public class Classroom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String code;
    private String semester;

    @OneToOne(cascade=CascadeType.ALL)
    private Location defaultLocation;

    @ManyToOne(cascade = CascadeType.ALL)
    private Professor professor;
    @ManyToMany(mappedBy = "classrooms")
    @ToString.Exclude
    private List<Student> students;
    @OneToMany(mappedBy = "classroom")
    @ToString.Exclude
    private List<Attendance> attendances;


    public Classroom(Long id, String name, String code, String semester, Location defaultLocation, Professor professor, List<Student> students, List<Attendance> attendances) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.semester = semester;
        this.defaultLocation = defaultLocation;
        this.professor = professor;
        this.students = students;
        this.attendances = attendances;
    }
}