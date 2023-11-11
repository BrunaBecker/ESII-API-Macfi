package com.macfi.controller;

import com.macfi.payload.VirtualZoneDto;
import com.macfi.repository.VirtualZoneRepository;
import com.macfi.service.VirtualZoneService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("virtualZone")
public class VirtualZoneController {

    @Autowired
    private VirtualZoneService virtualZoneService;

    @Operation(
            summary = "Get VirtualZone REST API",
            description = "Get VirtualZone REST API is used to get all post from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 OK"
    )
    @GetMapping
    public ResponseEntity<List<VirtualZoneDto>> getVirtualZones() {
        return ResponseEntity.ok(virtualZoneService.getVirtualZones());
    }

    @PostMapping
    public ResponseEntity<VirtualZoneDto> createVirtualZone(@RequestBody VirtualZoneDto virtualZoneDto) {
        return new ResponseEntity<>(virtualZoneService.createVirtualZone(virtualZoneDto), org.springframework.http.HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<VirtualZoneDto> updateVirtualZone(@RequestBody VirtualZoneDto virtualZoneDto) {
        return ResponseEntity.ok(virtualZoneService.updateVirtualZone(virtualZoneDto));
    }

    @GetMapping("/{virtualZoneId}")
    public ResponseEntity<VirtualZoneDto> getVirtualZoneById(@PathVariable Long virtualZoneId) {
        return ResponseEntity.ok(virtualZoneService.getVirtualZoneById(virtualZoneId));
    }

}
