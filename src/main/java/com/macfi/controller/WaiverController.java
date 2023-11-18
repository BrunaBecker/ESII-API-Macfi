package com.macfi.controller;

import com.macfi.payload.WaiverDto;
import com.macfi.service.WaiverService;
import com.macfi.exception.EntityNotFoundException;
import com.macfi.exception.UserUnauthorized;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    public ResponseEntity<WaiverDto> createWaiver(@RequestBody WaiverDto waiverDto) {
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
        } catch (EntityNotFoundException | UserUnauthorized ae){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
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
        } catch (EntityNotFoundException | UserUnauthorized ae){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }


}
