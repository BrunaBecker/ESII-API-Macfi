package com.macfi.model.person;

import com.macfi.model.utils.RegisterCollegeID;
import jakarta.persistence.Entity;
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

    public RegisterStudent(String identifier, Date dateStarted, Date dateFinished, boolean isActive, Student student) {
        super(identifier, dateStarted, dateFinished, isActive, student);
    }
}
