package com.macfi.controller;


import com.macfi.model.Event;
import com.macfi.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:3599")
@RestController
@RequestMapping("event")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping("byDateBetween")
    public List<Event> getByDateBetween(@RequestParam String startDate, @RequestParam String endDate) {
        return eventService.getEventByDateBetween(startDate, endDate);
    }
}
