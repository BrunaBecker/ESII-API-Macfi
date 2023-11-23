package com.macfi.payload;

import com.macfi.model.Event;
import com.macfi.model.utils.enums_class.EventStatus;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link Event}
 */
@Data
public class EventDto implements Serializable {
    Long id;
    @NotNull(message = "name must be not null")
    String name;
    @NotNull(message = "date must be not null")
    @FutureOrPresent(message = "date cannot be in past")
    Date date;
    @NotNull
    @Size(message = "max characteres 500", max = 500)
    String description;

    @NotNull(message = "event must be a status")
    EventStatus status;

    ClassroomDto classroom;
}
