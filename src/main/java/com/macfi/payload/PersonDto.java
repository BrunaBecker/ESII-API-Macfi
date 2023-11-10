package com.macfi.payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link com.macfi.model.person.Person}
 */
@Data
public class PersonDto implements Serializable {
    @NotNull(message = "id must not be null")
    Long id;
    @NotNull(message = "name must not be null")
    @NotEmpty(message = "name must not be empty")
    String name;
    @NotNull(message = "socialName must not be null")
    String socialName;
    @NotNull(message = "birthDate must not be null")
    Date birthDate;
    Boolean isActive;
    @NotEmpty(message = "cpf must not be empty")
    String cpf;
    @NotEmpty(message = "email must not be empty")
    String email;
}