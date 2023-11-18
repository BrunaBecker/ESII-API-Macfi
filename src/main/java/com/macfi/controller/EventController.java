package com.macfi.controller;

import com.macfi.exception.EntityNotFoundException;
import com.macfi.exception.UserUnauthorized;
import com.macfi.payload.EventDto;
import com.macfi.service.EventService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        try {
            List<EventDto> events = eventService.getEvents();
            return ResponseEntity.ok(eventService.getEvents());
        } catch (EntityNotFoundException | UserUnauthorized ae){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping
    public ResponseEntity<EventDto> updateEvent(@Valid @RequestBody EventDto eventDto) {
        try {
            EventDto eventDto1 = eventService.updateEvent(eventDto);
            return ResponseEntity.ok(eventDto1);
        } catch (EntityNotFoundException | UserUnauthorized ae){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping
    public ResponseEntity<EventDto> createEvent(@Valid @RequestBody EventDto eventDto) {

        EventDto eventDto1;
        try {
            eventDto1 = eventService.createEvent(eventDto);
            return new ResponseEntity<>(eventDto1, HttpStatus.CREATED);
        } catch (EntityNotFoundException | UserUnauthorized ae){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("byDateBetween")
    public ResponseEntity<List<EventDto>> getByDateBetween(@RequestParam String startDate, @RequestParam String endDate) {
        try {
            List<EventDto> events = eventService.getEventByDateBetween(startDate, endDate);
            return ResponseEntity.ok(events);
        } catch (EntityNotFoundException | UserUnauthorized ae){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
