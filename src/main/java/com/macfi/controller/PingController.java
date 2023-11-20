package com.macfi.controller;


import com.macfi.exception.EntityNotFoundException;
import com.macfi.exception.UserUnauthorized;
import com.macfi.payload.PingDto;
import com.macfi.service.PingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("ping")
public class PingController {

    @Autowired
    private PingService pingService;

    @Operation(
            summary = "Create Ping REST API",
            description = "Create Ping REST API is used to save post into database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )
    @PostMapping
    public ResponseEntity<PingDto> createPing(@Valid @RequestBody PingDto pingDto) {
        PingDto pingDto1;
        try {
            pingDto1 = pingService.createPing(pingDto);
            return new ResponseEntity<>(pingDto1, HttpStatus.CREATED);
        } catch (EntityNotFoundException | UserUnauthorized ae) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PingDto> getPingById(@PathVariable(name = "id") Long id) {
        try {
            return ResponseEntity.ok(pingService.getPingById(id));
        } catch (EntityNotFoundException | UserUnauthorized ae) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }


}
