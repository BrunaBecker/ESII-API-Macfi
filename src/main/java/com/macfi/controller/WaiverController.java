package com.macfi.controller;

import com.macfi.model.Waiver;
import com.macfi.payload.WaiverDto;
import com.macfi.service.WaiverService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:8080")
@RestController
@RequestMapping("waiver")
public class WaiverController {

    @Autowired
    private WaiverService waiverService;

    @Operation(
            summary = "Create Waiver REST API",
            description = "Create Waiver REST API is used to save post into database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )
    @PostMapping
    public ResponseEntity<WaiverDto> createWaiver(@RequestBody WaiverDto waiverDto) {
        return new ResponseEntity<>(waiverService.createWaiver(waiverDto), org.springframework.http.HttpStatus.CREATED);
    }

    @GetMapping("byStudentAndClassroom") //http://localhost:8080/waiver/byStudentAndClassroom?idStudent=1&idClassroom=1
    public ResponseEntity<WaiverDto> getWaiverByStudentAndClassroom(@RequestParam Long idStudent, @RequestParam Long idClassroom) {
        return ResponseEntity.ok(waiverService.getWaiverByStudentAndClassroom(idStudent, idClassroom));
    }

    @GetMapping("{id}") //http://localhost:8080/waiver/1
    public ResponseEntity<WaiverDto> getWaiverById(@PathVariable Long id) {
        return ResponseEntity.ok(waiverService.getWaiverById(id));
    }


}
