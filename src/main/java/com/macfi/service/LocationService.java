package com.macfi.service;

import com.macfi.model.Location;
import com.macfi.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    public Location createLocation(Location Location) {
        return locationRepository.save(Location);
    }

    public Location getLocationById(Long id) {
        return locationRepository.findByID(id);
    }


    public List<Location> getLocationByProfessor(String identifier) {
        return locationRepository.findByProfessor(identifier);
    }

    public Location updateLocation(Location Location) {
        Location aLocation = getLocationById(Location.getId());
        if (!Location.getId().equals(aLocation.getId())) {
            locationRepository.findByID(Location.getId());
        }
        return locationRepository.save(Location);
    }

    public List<Location> getLocations() {
        return locationRepository.findAll(Sort.by("id"));
    }

}
