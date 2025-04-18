package com.example.mediclinic.service;

import com.example.mediclinic.model.User;
import com.example.mediclinic.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Save or update user
    public User save(User user) {
        return userRepository.save(user);
    }

    // Update an existing user
    public User update(Long id, User user) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            user.setId(id);  // Ensuring the ID is passed for update
            return userRepository.save(user);
        }
        throw new RuntimeException("User not found with ID: " + id);
    }

    // Delete user by ID
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    // Get all users
    public List<User> findAll() {
        return userRepository.findAll();
    }

    // Get user by ID
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
    }

    // Get user by Username
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found with Username: " + username));
    }

    // Get user by Email
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with Email: " + email));
    }
}
