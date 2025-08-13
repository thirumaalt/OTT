package com.example.cmsservice.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String category;
    private String genre;
    private String language;
    private String thumbnailUrl;
    private String videoUrl;
    private String trailerUrl;
    private LocalDateTime releaseDate;
    private boolean published;
}
