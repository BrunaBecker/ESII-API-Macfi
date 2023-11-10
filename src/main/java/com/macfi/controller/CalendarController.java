package com.macfi.controller;


import com.macfi.payload.CalendarDto;
import com.macfi.service.CalendarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:8080")
@RestController
@RequestMapping("calendar")
public class CalendarController {


    @Autowired
    private CalendarService calendarService;

    @GetMapping
    public ResponseEntity<CalendarDto> getCalendar(@Valid @RequestBody CalendarDto calendarDto) {
        return ResponseEntity.ok(calendarService.getCalendar(calendarDto));
    }

    @PostMapping
    public ResponseEntity<CalendarDto> createCalendar(@Valid @RequestBody CalendarDto calendarDto) {
        return ResponseEntity.ok(calendarService.createCalendar(calendarDto));
    }

    @PutMapping
    public ResponseEntity<CalendarDto> updateCalendar(@Valid @RequestBody CalendarDto calendarDto) {
        return ResponseEntity.ok(calendarService.updateCalendar(calendarDto));
    }

    @GetMapping("byId/{id}")
    public ResponseEntity<CalendarDto> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(calendarService.getById(id));
    }
}
