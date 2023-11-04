package com.macfi.model.person;

import com.macfi.model.utils.RegisterCollegeID;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RegisterStudent extends RegisterCollegeID {

    @OneToOne(mappedBy = "register")
    private Student student;

    public RegisterStudent(String identifier, Date dateStarted, Date dateFinished, boolean isActive, Person person, Student student) {
        super(identifier, dateStarted, dateFinished, isActive, person);
        this.student = student;
    }
}
