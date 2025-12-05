package com.geterdone.get_er_done.service;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.geterdone.get_er_done.model.UserLogin;
import com.geterdone.get_er_done.repository.UserLoginRepository;

@Service
public class UserLoginService {

    private final UserLoginRepository repository;

    public UserLoginService(UserLoginRepository repository) {
        this.repository = repository;
    }

    // Register a new user
    public void registerUser(UserLogin user) {
        String hashedPassword = BCrypt.hashpw(user.getPasswordHash(), BCrypt.gensalt());
        user.setPasswordHash(hashedPassword);
        repository.save(user);
    }

    // Authenticate login
    public boolean validateLogin(String username, String plainPassword) {
        UserLogin stored = repository.findByUsername(username);

        if (stored == null) return false;

        return BCrypt.checkpw(plainPassword, stored.getPasswordHash());
    }

    // Get a user's login info (not exposing password in real apps)
    public UserLogin getUserLogin(String username) {
        return repository.findByUsername(username);
    }
}