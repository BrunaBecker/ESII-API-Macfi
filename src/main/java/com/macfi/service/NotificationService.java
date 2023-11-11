package com.macfi.service;

import com.macfi.model.Notification;
import com.macfi.modelMapper.modelMapping;
import com.macfi.payload.NotificationDto;
import com.macfi.repository.NotificationRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    public List<NotificationDto> getActiveNotificationByPersonId(Long personId) {
        List<Notification> a = notificationRepository.findByPersonIdAndIsActive(personId);
        return a.stream().map(notification -> modelMapping.getInstance().mapToDto(notification, NotificationDto.class)).collect(java.util.stream.Collectors.toList());
    }

    public NotificationDto createNotification(NotificationDto notification) {
        return modelMapping.getInstance().mapToDto(notificationRepository.save(modelMapping.getInstance().mapToEntity(notification, Notification.class)), NotificationDto.class);
    }

    public boolean deleteNotification(NotificationDto notification) {
        Notification a = modelMapping.getInstance().mapToEntity(notification, Notification.class);
        a.setActive(false);
        notificationRepository.save(a);
        return true;
    }

    public boolean deleteNotificationById(Long id) {
        Notification notification = notificationRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        notification.setActive(false);
        notificationRepository.save(notification);
        return true;
    }

    public NotificationDto updateNotification(NotificationDto notification) {
        return modelMapping.getInstance().mapToDto(notificationRepository.save(modelMapping.getInstance().mapToEntity(notification, Notification.class)), NotificationDto.class);
    }
}
