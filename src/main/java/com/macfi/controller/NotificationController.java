package com.macfi.controller;

import com.macfi.payload.NotificationDto;
import com.macfi.service.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("localhost:8080")
@RestController
@RequestMapping("notification")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @Operation(
            summary = "Get Notification REST API",
            description = "Get Notification REST API is used to get all post from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 OK"
    )
    @GetMapping("{idPerson}") //localhost:8080/notification/1
    public ResponseEntity<List<NotificationDto>> getActiveNotificationByPersonId(@PathVariable("idPerson") Long personId) {
        return ResponseEntity.ok(notificationService.getActiveNotificationByPersonId(personId));
    }

    @Operation(
            summary = "Create Notification REST API",
            description = "Create Notification REST API is used to save post into database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )
    @PostMapping
    public NotificationDto createNotification(@RequestBody NotificationDto notification) {
        return notificationService.createNotification(notification);
    }

    @Operation(
            summary = "Delete Notification REST API",
            description = "Delete Notification REST API is used to delete post from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 OK"
    )
    @DeleteMapping
    public ResponseEntity<Boolean> deleteNotification(@RequestBody NotificationDto notification) {
        return ResponseEntity.ok(notificationService.deleteNotification(notification));
    }

    @Operation(
            summary = "Delete Notification REST API",
            description = "Delete Notification REST API is used to delete post from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 OK"
    )
    @DeleteMapping("{id}")//localhost:8080/notification/1
    public ResponseEntity<Boolean> deleteNotificationById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(notificationService.deleteNotificationById(id));
    }

    @Operation(
            summary = "Update Notification REST API",
            description = "Update Notification REST API is used to update post into database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 OK"
    )
    @PutMapping
    public ResponseEntity<NotificationDto> updateNotification(@RequestBody NotificationDto notification) {

        return ResponseEntity.ok(notificationService.updateNotification(notification));
    }
}
