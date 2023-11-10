package com.macfi.payload;

import com.macfi.model.Attendance;
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
    Date date;
    String supportingText;
    LocalTime startHour;
    LocalTime endHour;
    Duration duration;
    boolean isAutomatic;
    boolean isHappening;
    VirtualZoneDto virtualZoneId;
    ClassroomDto classroomId;
}