package com.macfi.payload;

import com.macfi.model.person.RegisterCollegeID;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link RegisterCollegeID}
 */
@Data
public class RegisterCollegeIDDto implements Serializable {
    Long id;
    @NotNull(message = "identifier must not be null")
    @NotEmpty(message = "identifier must not be empty")
    String identifier;
    @NotNull(message = "dateStarted must not be null")
    Date dateStarted;
    Date dateFinished;
    boolean isActive = true;
}