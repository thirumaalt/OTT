package com.example.notificationservice.controller;

import com.example.notificationservice.model.Notification;
import com.example.notificationservice.service.NotificationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notify")
public class NotificationController {

    private final NotificationService service;

    public NotificationController(NotificationService service) {
        this.service = service;
    }

    @PostMapping("/send")
    public Notification sendNotification(@RequestBody Notification notification) {
        return service.sendNotification(notification);
    }

    @GetMapping("/status/{id}")
    public Notification getNotification(@PathVariable Long id) {
        return service.getNotification(id);
    }
}
