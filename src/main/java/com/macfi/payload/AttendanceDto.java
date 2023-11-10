package com.macfi.payload;

import com.macfi.model.Attendance;
import lombok.Value;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Date;

/**
 * DTO for {@link Attendance}
 */
@Value
public class AttendanceDto implements Serializable {
    Long id;
    Date date;
    String supportingText;
    LocalTime startHour;
    LocalTime endHour;
    Duration duration;
    boolean isAutomatic;
    boolean isHappening;
}