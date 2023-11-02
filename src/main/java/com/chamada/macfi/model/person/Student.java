package com.chamada.macfi.model.person;

import com.chamada.macfi.model.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
//@ToString
public class Student extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "matriculaId", referencedColumnName = "id")
    private RegisterStudent registration;

    @ManyToMany
    @JoinTable(name = "aluno_turma", joinColumns = @JoinColumn(name = "alunoId", referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name = "turmaId", referencedColumnName = "id"))
    private List<Classroom> classrooms;

    @OneToMany
    @JoinColumn(name = "atestadoId", referencedColumnName = "id")
    private List<Waiver> waivers;

    @OneToMany
    @JoinColumn(name = "chamadaId", referencedColumnName = "id")
    private List<Attendance> attendances;

    public Student(String name, String socialName, String CPF, Date birthDate) {
        this.name = name;
        this.socialName = socialName;
        this.CPF = CPF;
        this.birthDate = birthDate;
    }

}
