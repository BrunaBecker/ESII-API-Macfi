package com.macfi.payload;

import com.macfi.model.AttendanceStatus;
import com.macfi.model.utils.enums_class.StudentAtAttendanceState;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link AttendanceStatus}
 */
@Data
public class AttendanceStatusDto implements Serializable {
    @NotNull(message = "id must not be null")
    Long id;
    @NotNull(message = "studentState must not be null")
    StudentAtAttendanceState studentState;
    @NotNull(message = "studentHasResponded must not be null")
    boolean studentHasResponded;
    @NotNull(message = "validated must not be null")
    boolean validated;
    @NotNull(message = "student must not be null")
    StudentDto student;
    @NotNull(message = "attendance must not be null")
    AttendanceDto attendance;
    @NotNull(message = "successfulPings must not be null")
    List<PingDto> successfulPings;
    @NotNull(message = "unsuccessfulPings must not be null")
    List<PingDto> unsuccessfulPings;
}