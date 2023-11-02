package com.macfi.controller;

import com.macfi.model.Location;
import com.macfi.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("Location")
public class LocationController {

    @Autowired
    private LocationService LocationService;

    @GetMapping
    public List<Location> getLocations() {
        return LocationService.getLocations();
    }

    @PostMapping
    public Location createLocation(@RequestBody Location Location) {
        return LocationService.createLocation(Location);
    }

    @DeleteMapping("{idLocation}")
    public void remnoverLocation(@PathVariable("idLocation") Long id) {
        LocationService.deleteLocation(id);
    }


    @PutMapping
    public Location updateLocation(@RequestBody Location Location) {
        return LocationService.updateLocation(Location);
    }

    @GetMapping("{id}")
    public Location getLocationById(@PathVariable("id") Long id) {
        return LocationService.getLocationById(id);
    }


}
