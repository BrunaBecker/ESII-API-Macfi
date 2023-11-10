package com.macfi.model;

import lombok.Value;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link com.macfi.model.person.Person}
 */
@Value
public class PersonDto implements Serializable {
    Long id;
    String name;
    String socialName;
    Date birthDate;
    Boolean isActive;
    String cpf;
    String email;
    String password;
}