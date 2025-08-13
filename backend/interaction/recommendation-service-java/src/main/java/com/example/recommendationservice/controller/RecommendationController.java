package com.example.recommendationservice.controller;

import com.example.recommendationservice.model.Recommendation;
import com.example.recommendationservice.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recommendations")
public class RecommendationController {

    @Autowired
    private RecommendationService service;

    @PostMapping("/add")
    public Recommendation add(@RequestParam Long userId, @RequestParam String content) {
        return service.addRecommendation(userId, content);
    }

    @GetMapping("/get")
    public List<Recommendation> get(@RequestParam Long userId) {
        return service.getRecommendations(userId);
    }
}
