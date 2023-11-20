package com.macfi.payload;

import com.macfi.model.Calendar;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link Calendar}
 */
@Data
public class CalendarDto implements Serializable {
    Long id;
    List<EventDto> events;
}