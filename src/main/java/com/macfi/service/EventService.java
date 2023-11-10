package com.macfi.service;


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

    public EventDto createEvent(EventDto eventDto) {
        Event event = modelMapping.getInstance().mapToEntity(eventDto, Event.class);
        return modelMapping.getInstance().mapToDto(eventRepository.save(event), EventDto.class);
    }


    public EventDto getEventByDate(Date date) {
        return modelMapping.getInstance().mapToDto(eventRepository.findByDate(date), EventDto.class);
    }


    public List<EventDto> getEvents() {
        List<Event> events = eventRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        return events.stream().map(event -> modelMapping.getInstance().mapToDto(event, EventDto.class)).collect(Collectors.toList());
    }
}
