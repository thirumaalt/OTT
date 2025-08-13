package com.example.recommendationservice.service;

import com.example.recommendationservice.model.Recommendation;
import com.example.recommendationservice.repository.RecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendationService {

    @Autowired
    private RecommendationRepository repository;

    public Recommendation addRecommendation(Long userId, String content) {
        Recommendation rec = new Recommendation();
        rec.setUserId(userId);
        rec.setRecommendedContent(content);
        return repository.save(rec);
    }

    public List<Recommendation> getRecommendations(Long userId) {
        return repository.findByUserId(userId);
    }
}
