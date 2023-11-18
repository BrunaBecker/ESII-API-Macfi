package com.macfi.controller;

import com.macfi.exception.EntityNotFoundException;
import com.macfi.exception.UserUnauthorized;
import com.macfi.payload.CalendarDto;
import com.macfi.service.CalendarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:8080")
@RestController
@RequestMapping("calendar")
public class CalendarController {


    @Autowired
    private CalendarService calendarService;

    @GetMapping
    public ResponseEntity<List<CalendarDto>> getCalendars() {
        try {
            return ResponseEntity.ok(calendarService.getCalendars());
        } catch (EntityNotFoundException | UserUnauthorized ae){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
    @Operation(
            summary = "Create Calendar REST API",
            description = "Create Calendar REST API is used to save post into database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )
    @PostMapping
    public ResponseEntity<CalendarDto> createCalendar(@Valid @RequestBody CalendarDto calendarDto) {
       CalendarDto calendarDto1;
        try {
          calendarDto1 = calendarService.createCalendar(calendarDto);
          return new ResponseEntity<>(calendarDto1, HttpStatus.CREATED);
       } catch (Exception e) {
              return ResponseEntity.badRequest().body(null);
       }
    }

    @PutMapping
    public ResponseEntity<CalendarDto> updateCalendar(@Valid @RequestBody CalendarDto calendarDto) {
        CalendarDto calendarDto1;
        try {
            calendarDto1 = calendarService.updateCalendar(calendarDto);
            return ResponseEntity.ok(calendarDto1);
        } catch (EntityNotFoundException | UserUnauthorized ae){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CalendarDto> getById(@PathVariable("id") Long id) {
        CalendarDto calendarDto;
        try {
            calendarDto = calendarService.getById(id);
            return ResponseEntity.ok(calendarDto);
        } catch (EntityNotFoundException | UserUnauthorized ae){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
