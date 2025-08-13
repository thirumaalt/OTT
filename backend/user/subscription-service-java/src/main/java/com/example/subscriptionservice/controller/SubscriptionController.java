package com.example.subscriptionservice.controller;

import com.example.subscriptionservice.model.Subscription;
import com.example.subscriptionservice.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subscription")
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    @PostMapping("/")
    public Subscription createSubscription(@RequestBody Subscription subscription) {
        return subscriptionService.createSubscription(subscription);
    }

    @GetMapping("/{userId}")
    public Subscription getSubscription(@PathVariable String userId) {
        return subscriptionService.getSubscription(userId);
    }
}
