package com.example.notificationservice.service;

import com.example.notificationservice.model.Notification;
import com.example.notificationservice.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NotificationService {

    private final NotificationRepository repository;

    public NotificationService(NotificationRepository repository) {
        this.repository = repository;
    }

    public Notification sendNotification(Notification notification) {
        notification.setStatus("SENT");
        notification.setSentAt(LocalDateTime.now());
        return repository.save(notification);
    }

    public Notification getNotification(Long id) {
        return repository.findById(id).orElse(null);
    }
}
