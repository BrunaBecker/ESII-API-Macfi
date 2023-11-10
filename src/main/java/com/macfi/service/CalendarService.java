package com.macfi.service;

import com.macfi.exception.EntityNotFoundException;
import com.macfi.model.Calendar;
import com.macfi.modelMapper.modelMapping;
import com.macfi.payload.CalendarDto;
import com.macfi.repository.CalendarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalendarService {

    @Autowired
    private CalendarRepository calendarRepository;

    public CalendarDto createCalendar(CalendarDto calendarDto) {
        return modelMapping.getInstance().mapToDto(calendarRepository.save(modelMapping.getInstance().mapToEntity(calendarDto, Calendar.class)), CalendarDto.class);
    }

    public CalendarDto updateCalendar(CalendarDto calendarDto) {
        Calendar calendar = modelMapping.getInstance().mapToEntity(calendarDto, Calendar.class);
        return modelMapping.getInstance().mapToDto(calendarRepository.save(calendar), CalendarDto.class);
    }

    public CalendarDto getCalendar(CalendarDto calendarDto) {
        Calendar c = modelMapping.getInstance().mapToEntity(calendarDto, Calendar.class);

        return modelMapping.getInstance().mapToDto(calendarRepository.findById(c.getId()).orElseThrow(() -> new EntityNotFoundException("Calendar not found")), CalendarDto.class);
    }

    public CalendarDto getById(Long id) {
        return modelMapping.getInstance().mapToDto(calendarRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Calendar not found")), CalendarDto.class);
    }
}
