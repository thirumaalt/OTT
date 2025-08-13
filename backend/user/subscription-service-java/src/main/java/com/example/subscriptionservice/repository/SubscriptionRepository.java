package com.example.subscriptionservice.repository;

import com.example.subscriptionservice.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    Subscription findByUserId(String userId);
}
