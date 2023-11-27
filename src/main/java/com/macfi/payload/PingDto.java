package com.macfi.payload;

import com.macfi.model.Ping;
import com.macfi.model.utils.enums_class.StatusPing;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link Ping}
 */
@Data
public class PingDto implements Serializable {
    Long id;

    @Size(min = 7, max = 15, message = "ip format not valid")
    String ip;

    @NotEmpty(message = "date is required")
    String date;

    StatusPing status;

    @NotNull(message = "isContinuos cannot be null")
    Boolean isContinuos;

    CoordinateDto coordinate;

    @NotNull(message = "attendanceStatus cannot be null")
    AttendanceStatusDto attendanceStatus;


}