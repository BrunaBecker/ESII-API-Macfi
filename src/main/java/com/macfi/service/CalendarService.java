package com.macfi.service;

import com.macfi.exception.EntityNotFoundException;
import com.macfi.model.Calendar;
import com.macfi.model.Event;
import com.macfi.modelMapper.modelMapping;
import com.macfi.payload.CalendarDto;
import com.macfi.repository.CalendarRepository;
import com.macfi.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalendarService {

    @Autowired
    private CalendarRepository calendarRepository;

    @Autowired
    private EventRepository eventRepository;

    public CalendarDto createCalendar(CalendarDto calendarDto) {
        return modelMapping.getInstance().mapToDto(calendarRepository.save(modelMapping.getInstance().mapToEntity(calendarDto, Calendar.class)), CalendarDto.class);
    }

    public CalendarDto updateCalendar(Long calendarId) {
        Calendar calendar = modelMapping.getInstance().mapToEntity(calendarId, Calendar.class);
        return modelMapping.getInstance().mapToDto(calendarRepository.save(calendar), CalendarDto.class);
    }

    public List<CalendarDto> getCalendars() {
        List<Calendar> calendars = calendarRepository.findAll();
        return calendars.stream().map(calendar -> modelMapping.getInstance().mapToDto(calendar, CalendarDto.class)).toList();
    }

    public CalendarDto getById(Long id) {
        return modelMapping.getInstance().mapToDto(calendarRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Calendar not found")), CalendarDto.class);
    }

    public CalendarDto removeEvent(Long idCalendar, Long idEvent) {
        Event event = eventRepository.findById(idEvent).orElseThrow(() -> new EntityNotFoundException("Event not found"));
        Calendar calendar = calendarRepository.findById(idCalendar).orElseThrow(() -> new EntityNotFoundException("Calendar not found"));
        calendar.getEvents().remove(event);
        return modelMapping.getInstance().mapToDto(calendarRepository.save(calendar), CalendarDto.class);
    }

    public CalendarDto setEvent(Long idCalendar, Long idEvent) {
        Calendar calendar = calendarRepository.findById(idCalendar).orElseThrow(() -> new EntityNotFoundException("Calendar not found"));
        Event event = eventRepository.findById(idEvent).orElseThrow(() -> new EntityNotFoundException("Event not found"));
        calendar.getEvents().add(event);
        return modelMapping.getInstance().mapToDto(calendarRepository.save(calendar), CalendarDto.class);
    }
}
