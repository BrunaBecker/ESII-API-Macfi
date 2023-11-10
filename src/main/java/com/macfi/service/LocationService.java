package com.macfi.service;

import com.macfi.model.Location;
import com.macfi.modelMapper.modelMapping;
import com.macfi.payload.LocationDto;
import com.macfi.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    public LocationDto createLocation(LocationDto Location) {
        return modelMapping.getInstance().mapToDto(locationRepository.save(modelMapping.getInstance().mapToEntity(Location, Location.class)), LocationDto.class);
    }

    public LocationDto getLocationById(Long id) {
        return modelMapping.getInstance().mapToDto(locationRepository.getReferenceById(id), LocationDto.class);
    }


    public List<LocationDto> getLocationByProfessor(String identifier) {
        List<Location> locations = locationRepository.findByProfessor(identifier);
        return locations.stream().map(location -> modelMapping.getInstance().mapToDto(location, LocationDto.class)).collect(java.util.stream.Collectors.toList());

    }

    public LocationDto updateLocation(LocationDto Location) {
        Location aLocation = modelMapping.getInstance().mapToEntity(getLocationById(modelMapping.getInstance().mapToEntity(Location, Location.class).getId()), Location.class);
        if (!Location.getId().equals(aLocation.getId())) {
            locationRepository.findByID(Location.getId());
        }
        return modelMapping.getInstance().mapToDto(locationRepository.save(aLocation), LocationDto.class);
    }

    public List<LocationDto> getLocations() {
        List<Location> locations = locationRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        return locations.stream().map(location -> modelMapping.getInstance().mapToDto(location, LocationDto.class)).collect(java.util.stream.Collectors.toList());
    }

}
