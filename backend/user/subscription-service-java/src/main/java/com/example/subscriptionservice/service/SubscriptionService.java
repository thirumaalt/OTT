package com.example.subscriptionservice.service;

import com.example.subscriptionservice.model.Subscription;
import com.example.subscriptionservice.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionRepository repository;

    public Subscription createSubscription(Subscription subscription) {
        return repository.save(subscription);
    }

    public Subscription getSubscription(String userId) {
        return repository.findByUserId(userId);
    }
}
