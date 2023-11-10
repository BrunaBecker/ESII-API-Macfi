package com.macfi.payload;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.macfi.model.Calendar}
 */
@Data
public class CalendarDto implements Serializable {
    @NotNull(message = "id must not be null")
    Long id;
    @NotNull
    List<EventDto> events;
}