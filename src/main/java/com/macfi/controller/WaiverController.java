package com.macfi.controller;

import com.macfi.exception.EntityNotFoundException;
import com.macfi.exception.UserUnauthorized;
import com.macfi.payload.WaiverDto;
import com.macfi.service.WaiverService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<WaiverDto> createWaiver(@Valid @RequestBody WaiverDto waiverDto) {
        try {
            WaiverDto waiverDto1 = waiverService.createWaiver(waiverDto);
            return new ResponseEntity<>(waiverDto1, org.springframework.http.HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @Operation(
            summary = "Get Waiver byStudentAndClassroom REST API",
            description = "Get Waiver REST API is used to get all post from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 OK"
    )
    @GetMapping("byStudentAndClassroom") //http://localhost:8080/waiver/byStudentAndClassroom?idStudent=1&idClassroom=1
    public ResponseEntity<WaiverDto> getWaiverByStudentAndClassroom(@RequestParam Long idStudent, @RequestParam Long idClassroom) {
        try {
            return ResponseEntity.ok(waiverService.getWaiverByStudentAndClassroom(idStudent, idClassroom));
        } catch (EntityNotFoundException | UserUnauthorized ae) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

    @Operation(
            summary = "Get Waiver By Id REST API",
            description = "Get Waiver REST API is used to get all post from database"
    )
    @GetMapping("{id}") //http://localhost:8080/waiver/1
    public ResponseEntity<WaiverDto> getWaiverById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(waiverService.getWaiverById(id));
        } catch (EntityNotFoundException | UserUnauthorized ae) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("setAttendanceStatus") //http://localhost:8080/waiver/setAttendanceStatus
    public ResponseEntity<WaiverDto> setAttendanceStatus(@RequestParam Long idWaiver, @RequestParam Long idAttendance) {
        try {
            WaiverDto waiverDto = waiverService.setAttendanceStatus(idWaiver, idAttendance);
            return new ResponseEntity<>(waiverDto, HttpStatus.OK);
        } catch (EntityNotFoundException | UserUnauthorized ae) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("setAccepted") //http://localhost:8080/waiver/setAccepted?idWaiver=1
    public ResponseEntity<WaiverDto> setAccepted(@RequestParam Long idWaiver) {
        try {
            WaiverDto waiverDto = waiverService.setAccepted(idWaiver);
            return new ResponseEntity<>(waiverDto, HttpStatus.OK);
        } catch (EntityNotFoundException | UserUnauthorized ae) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("setRejected") //http://localhost:8080/waiver/setRejected?idWaiver=1
    public ResponseEntity<WaiverDto> setRejected(@RequestParam Long idWaiver) {
        try {
            WaiverDto waiverDto = waiverService.setRejected(idWaiver);
            return new ResponseEntity<>(waiverDto, HttpStatus.OK);
        } catch (EntityNotFoundException | UserUnauthorized ae) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("setComment") //http://localhost:8080/waiver/setComment?idWaiver=1
    public ResponseEntity<WaiverDto> setComment(@RequestParam Long idWaiver, @RequestParam Long comment) {
        try {
            WaiverDto waiverDto = waiverService.setComment(idWaiver, comment);
            return new ResponseEntity<>(waiverDto, HttpStatus.OK);
        } catch (EntityNotFoundException | UserUnauthorized ae) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("setFile") //http://localhost:8080/waiver/setFile?idWaiver=1&fileId=1
    public ResponseEntity<WaiverDto> setFile(@RequestParam Long idWaiver, @RequestParam Long fileId) {
        try {
            WaiverDto waiverDto = waiverService.setFile(idWaiver, fileId);
            return new ResponseEntity<>(waiverDto, HttpStatus.OK);
        } catch (EntityNotFoundException | UserUnauthorized ae) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("setAcceptionDate") //http://localhost:8080/waiver/setAcceptionDate?idWaiver=1
    public ResponseEntity<WaiverDto> setAcceptionDate(@RequestParam Long idWaiver, @RequestParam String acceptionDate) {
        try {
            WaiverDto waiverDto = waiverService.setAcceptionDate(idWaiver, acceptionDate);
            return new ResponseEntity<>(waiverDto, HttpStatus.OK);
        } catch (EntityNotFoundException | UserUnauthorized ae) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }


}
