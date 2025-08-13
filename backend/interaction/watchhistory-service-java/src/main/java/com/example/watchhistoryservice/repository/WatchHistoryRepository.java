package com.example.watchhistoryservice.repository;

import com.example.watchhistoryservice.model.WatchHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WatchHistoryRepository extends JpaRepository<WatchHistory, Long> {
}
