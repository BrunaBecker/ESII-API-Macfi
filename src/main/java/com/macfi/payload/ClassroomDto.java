package com.macfi.payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.macfi.model.Classroom}
 */
@Data
public class ClassroomDto implements Serializable {
    Long id;
    @NotEmpty(message = "name  must be not empty")
    String name;
    @Size(message = "code must have  8<=x<=10 characteres", min = 8, max = 10)
    @NotEmpty(message = "code must be a valid value")
    String code;
    @NotEmpty(message = "semester can't be empty")
    String semester;
    @NotNull(message = "startHour can't be null")
    String startHour;
    @NotNull(message = "endHour can't be null")
    String endHour;
    @NotNull
    ProfessorDto professor;
    @NotNull
    @Size(min = 1, max = 200)
    List<StudentDto> students;


}