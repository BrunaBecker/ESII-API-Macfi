package com.macfi.model.utils;

import com.macfi.model.person.Person;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@ToString
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "register_college_id")
public abstract class RegisterCollegeID {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    protected String identifier;


    @Temporal(TemporalType.TIMESTAMP)
    protected Date dateStarted;

    @Temporal(TemporalType.TIMESTAMP)
    protected Date dateFinished;


    protected boolean isActive;

    @OneToOne
    protected Person person;

    public RegisterCollegeID(String identifier, Date dateStarted, Date dateFinished, boolean isActive, Person person) {
        this.identifier = identifier;
        this.dateStarted = dateStarted;
        this.dateFinished = dateFinished;
        this.isActive = isActive;
        this.person = person;
    }

}
