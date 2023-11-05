package com.macfi.model.person;

import com.macfi.model.utils.RegisterCollegeID;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
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
@PrimaryKeyJoinColumn(name = "id")
public class RegisterProfessor extends RegisterCollegeID {
    public RegisterProfessor(String identifier, Date dateStarted, Date dateFinished, boolean isActive, Person person) {
        super(identifier, dateStarted, dateFinished, isActive, person);
    }
}
