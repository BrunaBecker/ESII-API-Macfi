package com.macfi.service;

import com.macfi.exception.EntityNotFoundException;
import com.macfi.model.Location;
import com.macfi.model.VirtualZone;
import com.macfi.modelMapper.modelMapping;
import com.macfi.payload.VirtualZoneDto;
import com.macfi.repository.LocationRepository;
import com.macfi.repository.VirtualZoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VirtualZoneService {
    @Autowired
    private VirtualZoneRepository virtualZoneRepository;

    @Autowired
    private LocationRepository locationRepository;

    public List<VirtualZoneDto> getVirtualZones() {
        List<VirtualZone> virtualZones = virtualZoneRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        return virtualZones.stream().map(virtualZone -> modelMapping.getInstance().mapToDto(virtualZone, VirtualZoneDto.class)).collect(Collectors.toList());
    }

    public VirtualZoneDto createVirtualZone(VirtualZoneDto virtualZoneDto) {
        VirtualZone a = modelMapping.getInstance().mapToEntity(virtualZoneDto, VirtualZone.class);
        return modelMapping.getInstance().mapToDto(a, VirtualZoneDto.class);
    }

    public VirtualZoneDto updateVirtualZone(VirtualZoneDto virtualZoneDto) {
        return modelMapping.getInstance().mapToDto(virtualZoneRepository.save(modelMapping.getInstance().mapToEntity(virtualZoneDto, VirtualZone.class)), VirtualZoneDto.class);
    }

    public VirtualZoneDto getVirtualZoneById(Long virtualZoneId) {
        return modelMapping.getInstance().mapToDto(virtualZoneRepository.getReferenceById(virtualZoneId), VirtualZoneDto.class);
    }
}
