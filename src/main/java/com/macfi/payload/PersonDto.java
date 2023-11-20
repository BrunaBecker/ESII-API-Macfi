package com.macfi.payload;

import com.macfi.model.person.Person;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link Person}
 */
@Data
public class PersonDto implements Serializable {
    Long id;
    @NotNull(message = "name must not be null")
    @NotEmpty(message = "name must not be empty")
    String name;
    @NotNull(message = "socialName must not be null")
    String socialName;
    @NotNull(message = "birthDate must not be null")
    Date birthDate;
    Boolean isActive = true;
    @NotEmpty(message = "cpf must not be empty")
    String cpf;
    @NotEmpty(message = "email must not be empty")
    String email;
    @NotNull(message = "register must not be null")
    RegisterCollegeIDDto register;


}