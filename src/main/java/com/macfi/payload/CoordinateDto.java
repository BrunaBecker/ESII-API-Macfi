package com.macfi.payload;

import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.macfi.model.utils.Coordinate}
 */
@Value
public class CoordinateDto implements Serializable {
    Long id;
    @NotNull(message = "Latitude cannot be null")
    Double latitude;
    @NotNull(message = "Longitude cannot be null")
    Double longitude;
}