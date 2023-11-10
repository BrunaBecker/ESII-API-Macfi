package com.macfi.payload;

import lombok.Value;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.macfi.model.Calendar}
 */
@Value
public class CalendarDto implements Serializable {
    Long id;
    List<EventDto> events;
}