package com.macfi.controller;

import com.macfi.payload.VirtualZoneDto;
import com.macfi.exception.EntityNotFoundException;
import com.macfi.exception.UserUnauthorized;
import com.macfi.service.VirtualZoneService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        try {
            List<VirtualZoneDto> virtualZoneDto = virtualZoneService.getVirtualZones();
            return ResponseEntity.ok(virtualZoneDto);
        } catch (EntityNotFoundException | UserUnauthorized ae){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
    @Operation(
            summary = "Create VirtualZone REST API",
            description = "Create VirtualZone REST API is used to save post into database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )
    @PostMapping
    public ResponseEntity<VirtualZoneDto> createVirtualZone(@RequestBody VirtualZoneDto virtualZoneDto) {
        try {
            VirtualZoneDto virtualZoneDto1 = virtualZoneService.createVirtualZone(virtualZoneDto);
            return new ResponseEntity<>(virtualZoneDto1, org.springframework.http.HttpStatus.CREATED);
        } catch (EntityNotFoundException | UserUnauthorized ae){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping
    public ResponseEntity<VirtualZoneDto> updateVirtualZone(@RequestBody VirtualZoneDto virtualZoneDto) {
       try {
              VirtualZoneDto virtualZoneDto1 = virtualZoneService.updateVirtualZone(virtualZoneDto);
              return ResponseEntity.ok(virtualZoneDto1);
         } catch (Exception e) {
              return ResponseEntity.badRequest().body(null);
       }
    }

    @GetMapping("/{virtualZoneId}")
    public ResponseEntity<VirtualZoneDto> getVirtualZoneById(@PathVariable Long virtualZoneId) {
        try {
            return ResponseEntity.ok(virtualZoneService.getVirtualZoneById(virtualZoneId));
        } catch (EntityNotFoundException | UserUnauthorized ae){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

}
