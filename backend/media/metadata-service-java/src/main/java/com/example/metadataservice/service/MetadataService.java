package com.example.metadataservice.service;

import com.example.metadataservice.model.Metadata;
import com.example.metadataservice.repository.MetadataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MetadataService {

    @Autowired
    private MetadataRepository repository;

    @Autowired
    private TMDbService tmdbService;

    public Metadata save(Metadata metadata) {
        // Fetch data from TMDb
        String tmdbData = tmdbService.fetchMovieMetadata(metadata.getTitle());
        metadata.setData(tmdbData);  // store full response or filter it later
        return repository.save(metadata);
    }

    public List<Metadata> getAll() {
        return repository.findAll();
    }

    public List<Metadata> getByType(String type) {
        return repository.findByType(type);
    }
}
