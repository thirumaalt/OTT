package com.example.userservice.service;

import com.example.userservice.model.User;
import com.example.userservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User createUser(User user) {
        user.setCreatedAt(LocalDateTime.now());
        return repository.save(user);
    }

    public User getUser(Long id) {
        return repository.findById(id).orElse(null);
    }
}
