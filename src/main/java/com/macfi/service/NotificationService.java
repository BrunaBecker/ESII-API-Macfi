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
        Notification notification1 = notificationRepository.findById(notification.getId()).orElseThrow(EntityNotFoundException::new);
        if (notification.getPersonId() == null || (!notification1.getPerson().getId().equals(notification.getPersonId())))
            throw new IllegalArgumentException("person id must not be null");

        notification1.setStatusNotification(notification.getStatusNotification());
        notification1.setSupportingText(notification.getSupportingText());
        notification1.setTitle(notification.getTitle());
        notification1.setActive(notification.isActive());
        notification1.setRead(notification.isRead());


        return modelMapping.getInstance().mapToDto(notificationRepository.save(notification1), NotificationDto.class);
    }

    public NotificationDto setReadNotification(Long id) {
        Notification notification = notificationRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        notification.setRead(true);
        return modelMapping.getInstance().mapToDto(notificationRepository.save(notification), NotificationDto.class);
    }
}
