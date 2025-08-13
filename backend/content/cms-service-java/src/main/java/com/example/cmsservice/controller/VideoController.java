package com.example.cmsservice.controller;

import com.example.cmsservice.model.Video;
import com.example.cmsservice.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cms")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @PostMapping("/videos")
    public Video addVideo(@RequestBody Video video) {
        return videoService.saveVideo(video);
    }

    @GetMapping("/videos")
    public List<Video> getAllVideos() {
        return videoService.getAllVideos();
    }

    @GetMapping("/videos/{id}")
    public Optional<Video> getVideoById(@PathVariable Long id) {
        return videoService.getVideoById(id);
    }

    @DeleteMapping("/videos/{id}")
    public void deleteVideo(@PathVariable Long id) {
        videoService.deleteVideo(id);
    }
}
