package com.example.metadataservice.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Metadata {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String type;

    private String description;  // ✅ Add this

    @Column(columnDefinition = "TEXT")
    private String data;         // ✅ Optional if needed

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getData() { return data; }
    public void setData(String data) { this.data = data; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
}
