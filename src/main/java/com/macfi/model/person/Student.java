package com.macfi.model.person;

import com.macfi.model.Attendance;
import com.macfi.model.Classroom;
import com.macfi.model.Waiver;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
//@ToString
public class Student extends Person {

    @OneToOne
    @JoinColumn(name = "registerStudentID", referencedColumnName = "id")
    private RegisterStudent registration;

    @ManyToMany
    @JoinTable(name = "student_class", joinColumns = @JoinColumn(name = "studentId", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "classId", referencedColumnName = "id"))
    private List<Classroom> classrooms;

    @OneToMany
    @JoinColumn(name = "atestadoId", referencedColumnName = "id")
    private List<Waiver> waivers;

    @OneToMany
    @JoinColumn(name = "chamadaId", referencedColumnName = "id")
    private List<Attendance> attendances;

}
