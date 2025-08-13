package com.example.analyticsservice.service;

import com.example.analyticsservice.entity.Analytics;
import com.example.analyticsservice.repository.AnalyticsRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AnalyticsService {

    private final AnalyticsRepository repository;

    public AnalyticsService(AnalyticsRepository repository) {
        this.repository = repository;
    }

    public Analytics recordAction(Long userId, String action, String contentId) {
        Analytics analytics = new Analytics();
        analytics.setUserId(userId);
        analytics.setAction(action);
        analytics.setContentId(contentId);
        analytics.setTimestamp(LocalDateTime.now());
        return repository.save(analytics);
    }

    public List<Analytics> getAllActions() {
        return repository.findAll();
    }
}
