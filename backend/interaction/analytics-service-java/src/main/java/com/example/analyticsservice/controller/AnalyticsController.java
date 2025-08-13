package com.example.analyticsservice.controller;

import com.example.analyticsservice.entity.Analytics;
import com.example.analyticsservice.service.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {

    @Autowired
    private AnalyticsService service;

    @PostMapping("/record")
    public Analytics record(@RequestParam Long userId,
                             @RequestParam String action,
                             @RequestParam String contentId) {
        return service.recordAction(userId, action, contentId);
    }

    @GetMapping("/all")
    public List<Analytics> getAll() {
        return service.getAllActions();
    }
}
