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
        try {
            return ResponseEntity.ok(settingService.getSetting(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<SettingDto>> getSettings() {
        try {
            return ResponseEntity.ok(settingService.getSettings());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
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

        SettingDto a;
        try {
            a = settingService.createSetting(setting);
            return new ResponseEntity<>(a, org.springframework.http.HttpStatus.CREATED);
         } catch (Exception e) {
              return ResponseEntity.badRequest().body(null);
       }
    }

    @PutMapping
    public ResponseEntity<SettingDto> updateSetting(@RequestBody SettingDto setting) {
        SettingDto settingDto1;
        try {
            settingDto1 = settingService.updateSetting(setting);
            return ResponseEntity.ok(settingDto1);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/person/{identify}")
    public ResponseEntity<SettingDto> getSettingByPersonIdentifier(@PathVariable Long identify) {
        SettingDto  settingDto;

        try {
            settingDto = settingService.getSettingByPersonIdentifier(identify);
            return ResponseEntity.ok(settingDto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

}
