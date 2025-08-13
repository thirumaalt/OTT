package com.example.recommendationservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "recommendation")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recommendation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String recommendedContent;
}
