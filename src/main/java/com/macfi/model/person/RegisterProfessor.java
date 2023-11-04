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


    public RegisterProfessor(String identifier, Date dateStarted, Date dateFinished, boolean isActive, Professor person) {
        super(identifier, dateStarted, dateFinished, isActive, person);
    }
}
