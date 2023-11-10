package com.macfi.controller;


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
        return new ResponseEntity<>(pingService.createPing(pingDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PingDto> getPingById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(pingService.getPingById(id));
    }


}
