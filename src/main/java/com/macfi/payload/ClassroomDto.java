package com.macfi.payload;

import com.macfi.model.Classroom;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.List;

/**
 * DTO for {@link Classroom}
 */
@Data
public class ClassroomDto implements Serializable {
    Long id;
    @NotNull(message = "courseName can't be null")
    @NotEmpty(message = "name  must be not empty")
    String courseName;
    @NotNull(message = "className can't be null")
    @NotEmpty(message = "className must be not empty")
    String className;
    @Size(message = "code must have  8<=x<=10 characteres", min = 8, max = 10)
    @NotEmpty(message = "code must be a valid value")
    String code;
    @NotEmpty(message = "semester can't be empty")
    String semester;
    @NotNull(message = "startHour can't be null")
    LocalTime startHour;
    @NotNull(message = "endHour can't be null")
    LocalTime endHour;
    @NotNull
    ProfessorDto professor;
    LocationDto defaultLocation;

    List<EventDto> events;
    @Size(min = 1, max = 200)
    List<StudentDto> students;



}