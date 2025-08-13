package com.example.metadataservice.repository;

import com.example.metadataservice.model.Metadata;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MetadataRepository extends JpaRepository<Metadata, Long> {
    List<Metadata> findByType(String type);
}
