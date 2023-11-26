package com.macfi.payload;

import com.macfi.model.Attendance;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Date;

/**
 * DTO for {@link Attendance}
 */
@Data
public class AttendanceDto implements Serializable {
    Long id;
    @NotNull(message = "date must not be null")
    Date date;
    @NotNull(message = "supportingText must not be null")
    String supportingText;
    @NotNull(message = "startHour must not be null")
    LocalTime startHour;
    @NotNull(message = "endHour must not be null")
    LocalTime endHour;

    @NotNull(message = "isAutomatic must not be null")
    boolean isAutomatic;
    @NotNull(message = "isHappening must not be null")
    boolean isHappening;

    VirtualZoneDto virtualZone;

    ClassroomDto classroom;
}