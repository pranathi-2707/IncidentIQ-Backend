package com.incidentiq.service;

import java.util.Objects;

import org.springframework.stereotype.Service;

import com.incidentiq.Model.User;
import com.incidentiq.dto.Logindto;
import com.incidentiq.dto.Userdto;
import com.incidentiq.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public void save(Userdto dto) {
        if (dto == null) {
            throw new IllegalArgumentException("User data is required");
        }

        String name = normalize(dto.getName());
        String email = normalize(dto.getEmail());
        String password = normalize(dto.getPassword());

        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            throw new IllegalArgumentException("Name, email, and password are required");
        }

        if (repository.existsByEmail(email)) {
            throw new IllegalArgumentException("Email already exists");
        }

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole("USER");

        repository.save(user);
    }

    public String login(Logindto dto) {
        if (dto == null) {
            return "Invalid login request";
        }

        String email = normalize(dto.getEmail());
        String password = normalize(dto.getPassword());

        if (email.isEmpty() || password.isEmpty()) {
            return "Invalid login request";
        }

        User user = repository.findByEmail(email).orElse(null);

        if (user == null) {
            return "User not found";
        }

        if (!Objects.equals(user.getPassword(), password)) {
            return "Invalid password";
        }

        return "Login successful";
    }

    private String normalize(String value) {
        return value == null ? "" : value.trim();
    }
}