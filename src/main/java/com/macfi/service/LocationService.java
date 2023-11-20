package com.macfi.service;

import com.macfi.exception.EntityNotFoundException;
import com.macfi.model.Classroom;
import com.macfi.model.Location;
import com.macfi.model.VirtualZone;
import com.macfi.model.utils.Coordinate;
import com.macfi.modelMapper.modelMapping;
import com.macfi.payload.CoordinateDto;
import com.macfi.payload.LocationDto;
import com.macfi.repository.ClassroomRepository;
import com.macfi.repository.CoordinateRepository;
import com.macfi.repository.LocationRepository;
import com.macfi.repository.VirtualZoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private VirtualZoneRepository virtualZoneRepository;

    @Autowired
    private CoordinateRepository coordinateRepository;

    @Autowired
    private ClassroomRepository classroomRepository;

    public LocationDto createLocation(LocationDto Location) {
        Location l = modelMapping.getInstance().mapToEntity(Location, Location.class);
        return modelMapping.getInstance().mapToDto(locationRepository.save(l), LocationDto.class);
    }

    public LocationDto getLocationById(Long id) {
        return modelMapping.getInstance().mapToDto(locationRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Location not found")), LocationDto.class);
    }


    public List<LocationDto> getLocationByProfessor(String identifier) {
        List<Location> locations = locationRepository.findByProfessor(identifier);
        return locations.stream().map(location -> modelMapping.getInstance().mapToDto(location, LocationDto.class)).collect(java.util.stream.Collectors.toList());

    }

    public LocationDto updateLocation(LocationDto Location) {
        Location l = locationRepository.findById(Location.getId()).orElseThrow(() -> new EntityNotFoundException("Location not found"));
        return modelMapping.getInstance().mapToDto(locationRepository.save(l), LocationDto.class);
    }

    public List<LocationDto> getLocations() {
        List<Location> locations = locationRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        return locations.stream().map(location -> modelMapping.getInstance().mapToDto(location, LocationDto.class)).collect(java.util.stream.Collectors.toList());
    }

    public LocationDto setVirtualZone(Long virtualZoneId, LocationDto location) {
        VirtualZone v = virtualZoneRepository.findById(virtualZoneId).orElseThrow(() -> new EntityNotFoundException("VirtualZone not found"));
        Location l = locationRepository.findById(location.getId()).orElseThrow(() -> new EntityNotFoundException("Location not found"));
        l.getVirtualZones().add(v);
        return modelMapping.getInstance().mapToDto(locationRepository.save(l), LocationDto.class);
    }

    public LocationDto addCoordinate(Long id, CoordinateDto coordinateDto) {
        Location l = locationRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Location not found"));
        Coordinate c;
        try {
            c = coordinateRepository.findById(coordinateDto.getId()).orElseThrow(() -> new EntityNotFoundException("Coordinate not found"));
        } catch (EntityNotFoundException e) {
            c = modelMapping.getInstance().mapToEntity(coordinateDto, Coordinate.class);
        }

        l.setCoordinate(c);
        return modelMapping.getInstance().mapToDto(locationRepository.save(l), LocationDto.class);
    }

    public LocationDto setVirtualZoneById(Long idLocation, Long idVirtualZone) {
        Location l = locationRepository.findById(idLocation).orElseThrow(() -> new EntityNotFoundException("Location not found"));
        VirtualZone v = virtualZoneRepository.findById(idVirtualZone).orElseThrow(() -> new EntityNotFoundException("VirtualZone not found"));
        l.getVirtualZones().add(v);
        return modelMapping.getInstance().mapToDto(locationRepository.save(l), LocationDto.class);
    }

    public LocationDto setClassroom(Long idLocation, Long idClassroom) {
        Location l = locationRepository.findById(idLocation).orElseThrow(() -> new EntityNotFoundException("Location not found"));
        Classroom c = classroomRepository.findById(idClassroom).orElseThrow(() -> new EntityNotFoundException("Classroom not found"));
        l.setClassroom(c);
        return modelMapping.getInstance().mapToDto(locationRepository.save(l), LocationDto.class);
    }
}
