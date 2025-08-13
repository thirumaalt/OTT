package com.example.userinteraction.controller;

import com.example.userinteraction.model.Interaction;
import com.example.userinteraction.service.InteractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/interaction")
public class InteractionController {

    @Autowired
    private InteractionService service;

    @PostMapping("/add")
    public Interaction add(@RequestBody Interaction interaction) {
        return service.save(interaction);
    }

    @GetMapping("/all")
    public List<Interaction> getAll() {
        return service.getAll();
    }
}
