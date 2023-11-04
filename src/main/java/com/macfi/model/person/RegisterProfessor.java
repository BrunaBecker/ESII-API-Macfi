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
public class RegisterProfessor extends RegisterCollegeID {

    @OneToOne
    private Professor professor;

    public RegisterProfessor(String identifier, Date dateStarted, Date dateFinished, boolean isActive, Person person) {
        super(identifier, dateStarted, dateFinished, isActive, person);
    }
}
