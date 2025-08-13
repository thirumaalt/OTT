package com.example.userinteraction.service;

import com.example.userinteraction.model.Interaction;
import com.example.userinteraction.repository.InteractionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InteractionService {

    @Autowired
    private InteractionRepository repository;

    public Interaction save(Interaction interaction) {
        return repository.save(interaction);
    }

    public List<Interaction> getAll() {
        return repository.findAll();
    }
}
