package com.example.cmsservice.repository;

import com.example.cmsservice.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video, Long> {
}
