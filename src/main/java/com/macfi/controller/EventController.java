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
            return ResponseEntity.ok(events);
        } catch (EntityNotFoundException | UserUnauthorized ae) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping
    public ResponseEntity<EventDto> updateEvent(@Valid @RequestBody EventDto eventDto) {
        try {
            EventDto eventDto1 = eventService.updateEvent(eventDto);
            return ResponseEntity.ok(eventDto1);
        } catch (EntityNotFoundException | UserUnauthorized ae) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping
    public ResponseEntity<EventDto> createEvent(@Valid @RequestBody EventDto eventDto) {

        EventDto eventDto1;
        try {
            eventDto1 = eventService.createEvent(eventDto);
            return new ResponseEntity<>(eventDto1, HttpStatus.CREATED);
        } catch (EntityNotFoundException | UserUnauthorized ae) {
            System.out.println(ae.getMessage());
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("byDateBetween")//localhost:8080/event/byDateBetween?startDate=2021-06-01&endDate=2021-06-30
    public ResponseEntity<List<EventDto>> getByDateBetween(@RequestParam String startDate, @RequestParam String endDate) {
        try {
            List<EventDto> events = eventService.getEventByDateBetween(startDate, endDate);
            return ResponseEntity.ok(events);
        } catch (EntityNotFoundException | UserUnauthorized ae) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("byDateBetweenAndClassroomId")//localhost:8080/event/byDateAndClassroomId?date=2021-06-01&classroomId=1
    public ResponseEntity<List<EventDto>> getEventByDateBetweenAndClassroomId(@RequestParam String startDate, @RequestParam String endDate, @RequestParam Long classroomId) {
        try {
            List<EventDto> events = eventService.getEventByDateBetweenAndClassroomId(startDate, endDate, classroomId);
            return ResponseEntity.ok(events);
        } catch (EntityNotFoundException | UserUnauthorized ae) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("byClassroomId")//localhost:8080/event/byClassroomId?classroomId=1
    public ResponseEntity<List<EventDto>> getEventByClassroomId(@RequestParam Long classroomId) {
        try {
            List<EventDto> events = eventService.getEventByClassroomId(classroomId);
            return ResponseEntity.ok(events);
        } catch (EntityNotFoundException | UserUnauthorized ae) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("byDateBetweenAndStudentId")//localhost:8080/event/byDateBetweenAndStudentId?startDate=2021-06-01&endDate=2021-06-30&studentId=1
    public ResponseEntity<List<EventDto>> getEventByDateBetweenAndStudentId(@RequestParam String startDate, @RequestParam String endDate, @RequestParam Long studentId) {
        try {
            List<EventDto> events = eventService.getEventByDateBetweenAndStudentId(startDate, endDate, studentId);
            return ResponseEntity.ok(events);
        } catch (EntityNotFoundException | UserUnauthorized ae) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("byDateBetweenAndProfessorId")//localhost:8080/event/byDateBetweenAndProfessorId?startDate=2021-06-01&endDate=2021-06-30&professorId=1
    public ResponseEntity<List<EventDto>> getEventByDateBetweenAndProfessorId(@RequestParam String startDate, @RequestParam String endDate, @RequestParam Long professorId) {
        try {
            List<EventDto> events = eventService.getEventByDateBetweenAndProfessorId(startDate, endDate, professorId);
            return ResponseEntity.ok(events);
        } catch (EntityNotFoundException | UserUnauthorized ae) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }


}
