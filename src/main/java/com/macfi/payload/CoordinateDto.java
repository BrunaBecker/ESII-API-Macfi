package com.macfi.payload;

import com.macfi.model.utils.Coordinate;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

/**
 * DTO for {@link Coordinate}
 */
@Data
public class CoordinateDto implements Serializable {
    Long id;
    @NotNull(message = "Latitude cannot be null")
    Double latitude;
    @NotNull(message = "Longitude cannot be null")
    Double longitude;
}