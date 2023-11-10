package com.macfi.controller;

import com.macfi.payload.LocationDto;
import com.macfi.service.LocationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:8080")
@RestController
@RequestMapping("location")
public class LocationController {

    @Autowired
    private LocationService LocationService;

    @GetMapping
    public ResponseEntity<List<LocationDto>> getLocations() {
        return ResponseEntity.ok(LocationService.getLocations());
    }

    @Operation(
            summary = "Create Location REST API",
            description = "Create Location REST API is used to save post into database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )

    @PostMapping
    public ResponseEntity<LocationDto> createLocation(@RequestBody LocationDto Location) {
        return new ResponseEntity<>(LocationService.createLocation(Location), org.springframework.http.HttpStatus.CREATED);
    }


    @PutMapping
    public ResponseEntity<LocationDto> updateLocation(@RequestBody LocationDto Location) {
        return ResponseEntity.ok(LocationService.updateLocation(Location));
    }


    @GetMapping("byProfessor/{identifier}")
    public ResponseEntity<List<LocationDto>> getLocationByProfessor(@PathVariable("identifier") String identifier) {
        List<LocationDto> locations = LocationService.getLocationByProfessor(identifier);
        return ResponseEntity.ok(locations);
    }
}
