package com.macfi.service;


import com.macfi.model.Event;
import com.macfi.model.utils.Dateformater;
import com.macfi.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public List<Event> getEventByDateBetween(String start, String end) {
        Date dateStart = null;
        Date dateEnd = null;

        try {
            dateStart = Dateformater.format(start);
            dateEnd = Dateformater.format(end);
            return eventRepository.findAllByDateBetween(dateStart, dateEnd);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }


    public Event getEventByDate(Date date) {
        return eventRepository.findByDate(date);
    }


}
