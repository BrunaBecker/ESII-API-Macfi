package com.macfi.payload;

import com.macfi.model.Calendar;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link Calendar}
 */
@Data
public class CalendarDto implements Serializable {
    @NotNull(message = "id must not be null")
    Long id;
    @NotNull
    List<EventDto> events;
}