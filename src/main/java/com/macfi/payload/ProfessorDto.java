package com.macfi.payload;

import com.macfi.model.person.Professor;
import com.macfi.model.utils.enums_class.UserType;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link Professor}
 */
@Data
public class ProfessorDto implements Serializable {
    final UserType type = UserType.professor;
    Long id;
    @NotEmpty(message = "name must be not empty")
    String name;
    String socialName;
    @NotNull(message = "brithdate must have a value")
    @Past(message = "birthdate must be in past")
    Date birthDate;
    @NotNull
    Boolean isActive;
    @NotNull
    @Pattern(message = "must be in cpf format", regexp = "([0-9]{2}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[\\/]?[0-9]{4}[-]?[0-9]{2})|([0-9]{3}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[-]?[0-9]{2})")
    @NotEmpty
    String cpf;
    @Email(message = "Email  invalid format")
    @NotEmpty
    String email;
    @NotEmpty
    String password;
    @NotNull
    RegisterCollegeIDDto register;
    SettingDto setting;
    PictureDto profileImage;
}