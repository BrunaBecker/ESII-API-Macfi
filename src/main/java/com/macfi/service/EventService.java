package com.macfi.service;


import com.macfi.exception.EntityNotFoundException;
import com.macfi.model.Event;
import com.macfi.model.utils.Dateformater;
import com.macfi.modelMapper.modelMapping;
import com.macfi.payload.EventDto;
import com.macfi.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;


    public EventDto createEvent(EventDto eventDto) {
        Event event = modelMapping.getInstance().mapToEntity(eventDto, Event.class);
        return modelMapping.getInstance().mapToDto(eventRepository.save(event), EventDto.class);
    }

    public EventDto updateEvent(EventDto eventDto) {
        eventRepository.findById(eventDto.getId()).orElseThrow(() -> new EntityNotFoundException("Event not found"));
        return modelMapping.getInstance().mapToDto(eventRepository.save(modelMapping.getInstance().mapToEntity(eventDto, Event.class)), EventDto.class);
    }


    public List<EventDto> getEvents() {
        List<Event> events = eventRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        return events.stream().map(event -> modelMapping.getInstance().mapToDto(event, EventDto.class)).collect(Collectors.toList());
    }

    public List<EventDto> getEventByDateBetween(String start, String end) {
        Date dateStart;
        Date dateEnd;
        try {
            dateStart = Dateformater.format(start);
            dateEnd = Dateformater.format(end);
            List<Event> events = eventRepository.findAllByDateBetween(dateStart, dateEnd);
            return events.stream().map(event -> modelMapping.getInstance().mapToDto(event, EventDto.class)).collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public List<EventDto> getEventByDateBetweenAndClassroomId(String startDate, String endDate, Long classroomId) {
        Date dateStart;
        Date dateEnd;
        try {
            dateStart = Dateformater.format(startDate);
            dateEnd = Dateformater.format(endDate);
            List<Event> events = eventRepository.findByClassroomIdAndDateBetween(classroomId, dateStart, dateEnd);
            return events.stream().map(event -> modelMapping.getInstance().mapToDto(event, EventDto.class)).collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
