package com.macfi.controller;

import com.macfi.exception.EntityNotFoundException;
import com.macfi.exception.UserUnauthorized;
import com.macfi.payload.CoordinateDto;
import com.macfi.payload.LocationDto;
import com.macfi.service.LocationService;
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
            System.out.println(e.getMessage());
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
    public ResponseEntity<LocationDto> createLocation(@Valid @RequestBody LocationDto Location) {
       LocationDto LocationDto1;
        try {
            LocationDto1 = LocationService.createLocation(Location);
            return new ResponseEntity<>(LocationDto1, org.springframework.http.HttpStatus.CREATED);
        } catch (EntityNotFoundException | UserUnauthorized ae){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }


    @PutMapping
    public ResponseEntity<LocationDto> updateLocation(@Valid @RequestBody LocationDto Location) {
        LocationDto LocationDto1;
        try {
            LocationDto1 = LocationService.updateLocation(Location);
            return ResponseEntity.ok(LocationDto1);
        } catch (EntityNotFoundException | UserUnauthorized ae){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("setVirtualZone/{id}")
    public ResponseEntity<LocationDto> setVirtualZone(@PathVariable("id") Long virtualZoneId, @RequestBody LocationDto Location) {
        LocationDto LocationDto1;
        try {
            LocationDto1 = LocationService.setVirtualZone(virtualZoneId, Location);
            return ResponseEntity.ok(LocationDto1);
        } catch (EntityNotFoundException | UserUnauthorized ae){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
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
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("addCoordinate") //localhost:8080/location/addCoordinate?idLocation=1
    public ResponseEntity<LocationDto> addCoordinate(@RequestParam("idLocation") Long id, @RequestBody CoordinateDto coordinateDto) {
        LocationDto LocationDto1;
        try {
            LocationDto1 = LocationService.addCoordinate(id, coordinateDto);
            return ResponseEntity.ok(LocationDto1);
        } catch (EntityNotFoundException | UserUnauthorized ae){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("setVirtualZoneById") //localhost:8080/location/setVirtualZone?idLocation=1&idVirtualZone=1
    public ResponseEntity<LocationDto> setVirtualZoneById(@RequestParam("idLocation") Long idLocation, @RequestParam("idVirtualZone") Long idVirtualZone) {
        LocationDto LocationDto1;
        try {
            LocationDto1 = LocationService.setVirtualZoneById(idLocation, idVirtualZone);
            return ResponseEntity.ok(LocationDto1);
        } catch (EntityNotFoundException | UserUnauthorized ae){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("setClassroom")//localhost:8080/location/setClassroom?idLocation=1&idClassroom=1
    public ResponseEntity<LocationDto> setClassroom(@RequestParam("idLocation") Long idLocation, @RequestParam("idClassroom") Long idClassroom) {
        LocationDto LocationDto1;
        try {
            LocationDto1 = LocationService.setClassroom(idLocation, idClassroom);
            return ResponseEntity.ok(LocationDto1);
        } catch (EntityNotFoundException | UserUnauthorized ae){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }





}
