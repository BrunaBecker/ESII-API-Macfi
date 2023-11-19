package com.macfi.controller;

import com.macfi.exception.EntityNotFoundException;
import com.macfi.exception.UserUnauthorized;
import com.macfi.payload.SettingDto;
import com.macfi.service.SettingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        } catch (EntityNotFoundException | UserUnauthorized ae) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<SettingDto>> getSettings() {
        try {
            return ResponseEntity.ok(settingService.getSettings());
        } catch (EntityNotFoundException | UserUnauthorized ae) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
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
    public ResponseEntity<SettingDto> createSetting(@Valid @RequestBody SettingDto setting) {

        SettingDto a;
        try {
            a = settingService.createSetting(setting);
            return new ResponseEntity<>(a, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping
    public ResponseEntity<SettingDto> updateSetting(@Valid @RequestBody SettingDto setting) {
        SettingDto settingDto1;
        try {
            settingDto1 = settingService.updateSetting(setting);
            return ResponseEntity.ok(settingDto1);
        } catch (EntityNotFoundException | UserUnauthorized ae) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/person/{identify}")
    public ResponseEntity<SettingDto> getSettingByPersonIdentifier(@PathVariable Long identify) {
        SettingDto settingDto;

        try {
            settingDto = settingService.getSettingByPersonIdentifier(identify);
            return ResponseEntity.ok(settingDto);
        } catch (EntityNotFoundException | UserUnauthorized ae) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/updateSettingPerson") //localhost:8080/setting/updateSettingPerson?idSetting=1&idPerson=1
    public ResponseEntity<SettingDto> updateSettingByPersonId(@RequestParam Long idSetting,  @RequestParam Long idPerson) {
        SettingDto settingDto1;
        try {
            settingDto1 = settingService.updateSettingByPersonId(idSetting, idPerson);
            return ResponseEntity.ok(settingDto1);
        } catch (EntityNotFoundException | UserUnauthorized ae) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }


}
