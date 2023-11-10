package com.macfi.model;

import lombok.Value;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link com.macfi.model.utils.RegisterCollegeID}
 */
@Value
public class RegisterCollegeIDDto implements Serializable {
    Long id;
    String identifier;
    Date dateStarted;
    Date dateFinished;
    boolean isActive;
    PersonDto person;
}