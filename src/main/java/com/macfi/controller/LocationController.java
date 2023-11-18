package com.macfi.controller;

import com.macfi.payload.LocationDto;
import com.macfi.service.LocationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.macfi.exception.EntityNotFoundException;
import com.macfi.exception.UserUnauthorized;

import java.util.List;

@CrossOrigin("http://localhost:8080")
@RestController
@RequestMapping("location")
public class LocationController {

    @Autowired
    private LocationService LocationService;

    @GetMapping
    public ResponseEntity<List<LocationDto>> getLocations() {
        try {
            return ResponseEntity.ok(LocationService.getLocations());
        } catch (EntityNotFoundException | UserUnauthorized ae){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
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
       LocationDto LocationDto1;
        try {
            LocationDto1 = LocationService.createLocation(Location);
            return new ResponseEntity<>(LocationDto1, org.springframework.http.HttpStatus.CREATED);
        } catch (EntityNotFoundException | UserUnauthorized ae){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }


    @PutMapping
    public ResponseEntity<LocationDto> updateLocation(@RequestBody LocationDto Location) {
        LocationDto LocationDto1;
        try {
            LocationDto1 = LocationService.updateLocation(Location);
            return ResponseEntity.ok(LocationDto1);
        } catch (EntityNotFoundException | UserUnauthorized ae){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("addVirtualZone/{id}")
    public ResponseEntity<LocationDto> addVirtualZone(@PathVariable("id") Long virtualZoneId, @RequestBody LocationDto Location) {
        LocationDto LocationDto1;
        try {
            LocationDto1 = LocationService.addVirtualZone(virtualZoneId, Location);
            return ResponseEntity.ok(LocationDto1);
        } catch (EntityNotFoundException | UserUnauthorized ae){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("byProfessor/{identifier}")
    public ResponseEntity<List<LocationDto>> getLocationByProfessor(@PathVariable("identifier") String identifier) {
        List<LocationDto> locationDto1;

        try {
            locationDto1 = LocationService.getLocationByProfessor(identifier);
            return ResponseEntity.ok(LocationService.getLocationByProfessor(identifier));
        } catch (EntityNotFoundException | UserUnauthorized ae){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
