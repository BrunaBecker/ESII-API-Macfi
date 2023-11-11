package com.macfi.controller;


import com.macfi.payload.SettingDto;
import com.macfi.service.SettingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("setting")
public class SettingController {

    @Autowired
    private SettingService settingService;


    @GetMapping("{id}")
    public ResponseEntity<SettingDto> getSettingById(@Valid @PathParam("id") Long id) {
        return ResponseEntity.ok(settingService.getSetting(id));
    }

    @GetMapping
    public ResponseEntity<List<SettingDto>> getSettings() {
        return ResponseEntity.ok(settingService.getSettings());
    }

    @Operation(
            summary = "Create Setting REST API",
            description = "Create Setting REST API is used to save post into database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )
    @PostMapping
    public ResponseEntity<SettingDto> createSetting(@RequestBody SettingDto setting) {
        return new ResponseEntity<>(settingService.createSetting(setting), org.springframework.http.HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<SettingDto> updateSetting(@RequestBody SettingDto setting) {
        return ResponseEntity.ok(settingService.updateSetting(setting));
    }

    @GetMapping("/person/{identify}")
    public ResponseEntity<SettingDto> getSettingByPersonIdentifier(@PathVariable Long identify) {
        return ResponseEntity.ok(settingService.getSettingByPersonIdentifier(identify));
    }

}
