package com.macfi.payload;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link com.macfi.model.utils.RegisterCollegeID}
 */
@Data
public class RegisterCollegeIDDto implements Serializable {
    Long id;
    String identifier;
    Date dateStarted;
    Date dateFinished;
    boolean isActive;
    PersonDto person;
}