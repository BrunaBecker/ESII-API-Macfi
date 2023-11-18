package com.macfi.payload;

import com.macfi.model.VirtualZone;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

/**
 * DTO for {@link VirtualZone}
 */
@Data
public class VirtualZoneDto implements Serializable {
    Long id;
    @NotNull(message = "locationId must not be null")
    Long locationId;
    @NotNull(message = "attendanceId must not be null")
    Long attendanceId;

}