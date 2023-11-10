package com.macfi.controller;


import com.macfi.payload.PingDto;
import com.macfi.service.PingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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
    public ResponseEntity<PingDto> createPing(PingDto pingDto) {
        return ResponseEntity.ok(pingService.createPing(pingDto));
    }






}
