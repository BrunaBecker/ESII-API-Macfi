package com.macfi.payload;

import com.macfi.model.Ping;
import com.macfi.model.utils.enums_class.StatusPing;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link Ping}
 */
@Value
public class PingDto implements Serializable {
    Long id;
    @NotEmpty(message = "ip is required")
    @Size(min = 7, max= 15, message = "ip format not valid")
    String ip;
    @NotEmpty(message = "date is required")
    Date date;

    @NotEmpty(message = "status is required")
    StatusPing status;

    @NotEmpty(message = "isContinuos is required")
    boolean isContinuos;


    Long coordinateId;
    Long attendanceStatusId;


}