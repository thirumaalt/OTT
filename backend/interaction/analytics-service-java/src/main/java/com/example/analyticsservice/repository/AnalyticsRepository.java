package com.example.analyticsservice.repository;

import com.example.analyticsservice.entity.Analytics;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnalyticsRepository extends JpaRepository<Analytics, Long> {
}
