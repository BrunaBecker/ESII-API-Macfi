package com.macfi.service;

import com.macfi.exception.EntityNotFoundException;
import com.macfi.model.Calendar;
import com.macfi.modelMapper.modelMapping;
import com.macfi.payload.CalendarDto;
import com.macfi.repository.CalendarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<CalendarDto> getCalendars() {
        List<Calendar> calendars = calendarRepository.findAll();
        return calendars.stream().map(calendar -> modelMapping.getInstance().mapToDto(calendar, CalendarDto.class)).toList();
    }

    public CalendarDto getById(Long id) {
        return modelMapping.getInstance().mapToDto(calendarRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Calendar not found")), CalendarDto.class);
    }
}
