package com.macfi.controller;


import com.macfi.payload.EventDto;
import com.macfi.service.EventService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:8080")
@RestController
@RequestMapping("event")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping()
    public ResponseEntity<List<EventDto>> getEvents() {
        List<EventDto> events = eventService.getEvents();
        return ResponseEntity.ok(events);
    }

    @PutMapping
    public ResponseEntity<EventDto> updateEvent(@Valid @RequestBody EventDto eventDto) {
         ;
        return ResponseEntity.ok(eventService.updateEvent(eventDto));
    }

    @PostMapping
    public ResponseEntity<EventDto> createEvent(@Valid @RequestBody EventDto eventDto) {
        return ResponseEntity.ok(eventService.createEvent(eventDto));
    }

    @GetMapping("byDateBetween")
    public ResponseEntity<List<EventDto>> getByDateBetween(@RequestParam String startDate, @RequestParam String endDate) {
        List<EventDto> events = eventService.getEventByDateBetween(startDate, endDate);
        return ResponseEntity.ok(events);
    }
}
