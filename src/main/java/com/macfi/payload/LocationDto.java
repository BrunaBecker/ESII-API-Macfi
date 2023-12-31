package com.macfi.payload;

import com.macfi.model.Location;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link Location}
 */
@Data
public class LocationDto implements Serializable {
    @Positive(message = "Id must be positive")
    Long id;
    @NotNull(message = "Title cannot be null")
    @Size(max = 300, message = "Title must be between 0 and 300 characters")
    String title;
    @NotNull(message = "Description cannot be null")
    @Size(max = 300, message = "Description must be between 0 and 300 characters")
    String description;
    boolean isActive = true;
    CoordinateDto coordinate;
//    ProfessorDto professor;
//    ClassroomDto classroom;
}