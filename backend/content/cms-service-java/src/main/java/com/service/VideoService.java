package com.example.cmsservice.service;

import com.example.cmsservice.model.Video;
import com.example.cmsservice.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VideoService {

    @Autowired
    private VideoRepository repository;

    public Video saveVideo(Video video) {
        return repository.save(video);
    }

    public List<Video> getAllVideos() {
        return repository.findAll();
    }

    public Optional<Video> getVideoById(Long id) {
        return repository.findById(id);
    }

    public void deleteVideo(Long id) {
        repository.deleteById(id);
    }
}
