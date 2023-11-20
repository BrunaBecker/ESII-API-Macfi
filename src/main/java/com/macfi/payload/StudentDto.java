package com.macfi.payload;

import com.macfi.model.person.Student;
import com.macfi.model.utils.enums_class.UserType;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link Student}
 */
@Data
public class StudentDto implements Serializable {
    final UserType type = UserType.student;
    @NotNull(message = "id must be a value")
    @Positive(message = "id must be a positive Long")
    Long id;
    @NotNull(message = "name cannot be null")
    @NotEmpty(message = "name cannot be empty")
    String name;
    String socialName;
    @NotNull(message = "birthdate must not be null")
    @Past(message = "birthdate must be in past")
    Date birthDate;
    @NotNull(message = "student is active must have a value")
    Boolean isActive;
    @NotNull(message = "cpf must not be null")
    @Pattern(message = "cpf must be in valid format", regexp = "([0-9]{2}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[\\/]?[0-9]{4}[-]?[0-9]{2})|([0-9]{3}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[-]?[0-9]{2})")
    @NotEmpty(message = "cpf must have a value")
    String cpf;
    @Email(message = "invalid email format")
    String email;
    @NotNull(message = "password must not be null")
    @NotEmpty(message = "password must not be empty")
    String password;
    @NotNull
    RegisterCollegeIDDto register;
    SettingDto setting;
    PictureDto profileImage;
}