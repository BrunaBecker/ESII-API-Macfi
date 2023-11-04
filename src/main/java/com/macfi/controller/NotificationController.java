package com.macfi.controller;

import com.macfi.model.Notification;
import com.macfi.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("localhost:5871")
@RestController
@RequestMapping("notification")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("{idPerson}") //localhost:5871/notification/1
    public List<Notification> getActiveNotificationByPersonId(@PathVariable("idPerson") Long personId) {
        return notificationService.getActiveNotificationByPersonId(personId);
    }

    @PostMapping
    public Notification createNotification(@RequestBody Notification notification) {
        return notificationService.createNotification(notification);
    }

    @DeleteMapping
    public boolean deleteNotification(@RequestBody Notification notification) {
        return notificationService.deleteNotification(notification);
    }

    @DeleteMapping("{id}")//localhost:5871/notification/1
    public boolean deleteNotificationById(@PathVariable("id") Long id) {
        return notificationService.deleteNotificationById(id);
    }

    @PutMapping
    public Notification updateNotification(@RequestBody Notification notification) {
        return notificationService.createNotification(notification);
    }
}
