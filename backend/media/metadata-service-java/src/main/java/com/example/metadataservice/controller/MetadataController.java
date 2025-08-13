package com.example.metadataservice.controller;

import com.example.metadataservice.model.Metadata;
import com.example.metadataservice.service.MetadataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/metadata")
public class MetadataController {

    @Autowired
    private MetadataService service;

    @PostMapping("/add")
    public Metadata add(@RequestBody Metadata metadata) {
        System.out.println("Title: " + metadata.getTitle()); // ✅ Will now work
        return service.save(metadata);
    }

    @GetMapping("/all")
    public List<Metadata> getAll() {
        return service.getAll();
    }

    @GetMapping("/type/{type}")
    public List<Metadata> getByType(@PathVariable String type) {
        return service.getByType(type);
    }
}
