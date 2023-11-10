package com.macfi.payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link com.macfi.model.utils.RegisterCollegeID}
 */
@Data
public class RegisterCollegeIDDto implements Serializable {
    @NotNull(message = "id must not be null")
    Long id;
    @NotNull(message = "identifier must not be null")
    @NotEmpty(message = "identifier must not be empty")
    String identifier;
    @NotNull(message = "dateStarted must not be null")
    Date dateStarted;
    @NotNull(message = "dateFinished must not be null")
    Date dateFinished;
    boolean isActive;
}