package com.macfi.service;

import com.macfi.model.Notification;
import com.macfi.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    public List<Notification> getActiveNotificationByPersonId(Long personId) {
        return notificationRepository.getActiveNotificationByPersonId(personId);
    }

    public Notification createNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    public boolean deleteNotification(Notification notification) {
        notification.setActive(false);
        notificationRepository.save(notification);
        return true;
    }

    public boolean deleteNotificationById(Long id) {
        Notification notification = notificationRepository.findById(id).get();
        notification.setActive(false);
        notificationRepository.save(notification);
        return true;
    }

    public Notification updateNotification(Notification notification) {
        return notificationRepository.save(notification);
    }
}
