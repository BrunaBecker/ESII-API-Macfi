package com.macfi.payload;

import com.macfi.model.AttendanceStatus;
import com.macfi.model.utils.enums_class.StudentAtAttendanceState;
import lombok.Value;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link AttendanceStatus}
 */
@Data
public class AttendanceStatusDto implements Serializable {
    Long id;
    StudentAtAttendanceState studentState;
    boolean studentHasResponded;
    boolean validated;
    StudentDto student;
    AttendanceDto attendance;
    List<PingDto> successfulPings;
}