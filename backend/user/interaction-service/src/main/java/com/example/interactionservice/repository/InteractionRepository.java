package com.example.userinteraction.repository;

import com.example.userinteraction.model.Interaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InteractionRepository extends JpaRepository<Interaction, Long> {
}
