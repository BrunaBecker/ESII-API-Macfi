package com.macfi.payload;

import com.macfi.model.VirtualZone;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Date;

/**
 * DTO for {@link VirtualZone}
 */
@Data
public class VirtualZoneDto implements Serializable {
    @NotNull(message = "id must not be null")
    Long id;
    @NotNull(message = "locationId must not be null")
    Long locationId;
    @NotNull(message = "attendanceId must not be null")
    Long attendanceId;

}