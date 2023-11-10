package com.macfi.controller;


import com.macfi.payload.CalendarDto;
import com.macfi.service.CalendarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:8080")
@RestController
@RequestMapping("calendar")
public class CalendarController {


    @Autowired
    private CalendarService calendarService;

    @GetMapping
    public CalendarDto getCalendar(@Valid @RequestBody CalendarDto calendarDto) {
        return calendarService.getCalendar(calendarDto);
    }

    @PostMapping
    public CalendarDto createCalendar(@Valid @RequestBody CalendarDto calendarDto) {
        return calendarService.createCalendar(calendarDto);
    }

    @PutMapping
    public CalendarDto updateCalendar(@Valid @RequestBody CalendarDto calendarDto) {
        return calendarService.updateCalendar(calendarDto);
    }

    @GetMapping("byId/{id}")
    public CalendarDto getById(@PathVariable("id") Long id) {
        return calendarService.getById(id);
    }
}
