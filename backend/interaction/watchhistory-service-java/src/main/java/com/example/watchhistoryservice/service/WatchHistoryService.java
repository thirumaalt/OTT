package com.example.watchhistoryservice.service;

import com.example.watchhistoryservice.model.WatchHistory;
import com.example.watchhistoryservice.repository.WatchHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WatchHistoryService {

    @Autowired
    private WatchHistoryRepository repository;

    public WatchHistory saveWatchHistory(Long userId, Long contentId) {
        WatchHistory wh = new WatchHistory();
        wh.setUserId(userId);
        wh.setContentId(contentId);
        wh.setWatchedAt(LocalDateTime.now());
        return repository.save(wh);
    }

    public List<WatchHistory> getAllHistory() {
        return repository.findAll();
    }
}
