package com.macfi.model;

import com.macfi.model.person.Professor;
import com.macfi.model.person.Student;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
import java.time.LocalTime;
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
    private String courseName;
    private String className;
    private String code;
    private String semester;


    @Temporal(TemporalType.TIME)
    private LocalTime startHour;

    @Temporal(TemporalType.TIME)
    private LocalTime endHour;


    @OneToOne(cascade = CascadeType.ALL, mappedBy = "classroom")

    private Location defaultLocation;

    @ManyToOne
    private Professor professor;

    @ManyToMany(mappedBy = "classrooms")
    @ToString.Exclude
    private List<Student> students;

    @OneToMany(mappedBy = "classroom", cascade = CascadeType.ALL)
    @ToString.Exclude

    private List<Attendance> attendances;


    public Classroom(String courseName, String className,String code, String semester, LocalTime startHour, LocalTime endHour ,Location defaultLocation, Professor professor, List<Student> students, List<Attendance> attendances) {
        this.courseName = courseName;
        this.code = code;
        this.className = className;
        this.semester = semester;
        this.startHour = startHour;
        this.endHour = endHour;
        this.defaultLocation = defaultLocation;
        this.professor = professor;
        this.students = students;
        this.attendances = attendances;
    }
}