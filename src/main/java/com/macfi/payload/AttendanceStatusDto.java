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
    Long id;
    @NotNull(message = "studentState must not be null")
    StudentAtAttendanceState studentState;

    boolean studentHasResponded = false;

    boolean validated = false;

    @NotNull(message = "student must not be null")
    StudentDto student;
    @NotNull(message = "attendance must not be null")
    AttendanceDto attendance;

}