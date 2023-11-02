package com.macfi.model.utils;

import com.macfi.model.person.Person;
import jakarta.persistence.Entity;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@ToString
@Getter
@Setter
@Entity
@MappedSuperclass
public abstract class RegisterCollegeID {

    protected String identifier;

    protected Date dateStarted;

    protected Date dateFinished;

    protected boolean isActive;

    @OneToOne
    protected Person person;
}
