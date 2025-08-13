package com.example.watchhistoryservice.controller;

import com.example.watchhistoryservice.model.WatchHistory;
import com.example.watchhistoryservice.service.WatchHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/watch-history")
public class WatchHistoryController {

    @Autowired
    private WatchHistoryService service;

    @PostMapping("/record")
    public WatchHistory recordWatch(@RequestParam Long userId, @RequestParam Long contentId) {
        return service.saveWatchHistory(userId, contentId);
    }

    @GetMapping("/all")
    public List<WatchHistory> getAll() {
        return service.getAllHistory();
    }
}
