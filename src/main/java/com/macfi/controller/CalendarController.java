package com.macfi.controller;

import com.macfi.exception.EntityNotFoundException;
import com.macfi.exception.UserUnauthorized;
import com.macfi.payload.CalendarDto;
import com.macfi.service.CalendarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:8080")
@RestController
@RequestMapping("calendar")
public class CalendarController {


    @Autowired
    private CalendarService calendarService;


    @PutMapping
    public ResponseEntity<CalendarDto> updateCalendar(@Valid @RequestBody CalendarDto calendarDto) {
        try {
            CalendarDto calendarDto1 = calendarService.updateCalendar(calendarDto);
            return ResponseEntity.ok(calendarDto1);
        } catch (EntityNotFoundException | UserUnauthorized ae) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping()
    public ResponseEntity<CalendarDto> getById() {
        CalendarDto calendarDto;
        try {
            calendarDto = calendarService.getById(1L);
            return ResponseEntity.ok(calendarDto);
        } catch (EntityNotFoundException | UserUnauthorized ae) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }


    @PutMapping("setEvent") //localhost:8080/calendar/setEvent?idCalendar=1&idEvent=1
    public ResponseEntity<CalendarDto> setEvent(@RequestParam("idCalendar") Long idCalendar, @RequestParam("idEvent") Long idEvent) {
        CalendarDto calendarDto1;
        try {
            calendarDto1 = calendarService.setEvent(idCalendar, idEvent);
            return ResponseEntity.ok(calendarDto1);
        } catch (EntityNotFoundException | UserUnauthorized ae) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("removeEvent")
    public ResponseEntity<CalendarDto> removeEvent(@RequestParam("idCalendar") Long idCalendar, @RequestParam("idEvent") Long idEvent) {
        CalendarDto calendarDto1;
        try {
            calendarDto1 = calendarService.removeEvent(idCalendar, idEvent);
            return ResponseEntity.ok(calendarDto1);
        } catch (EntityNotFoundException | UserUnauthorized ae) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }
}
