package com.skillsync.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillsync.model.User;
import com.skillsync.repository.UserRepository;

@Service // Marks this class as a service implementation
public class UserServiceImpl implements UserService {

	@Autowired // Injects UserRepository automatically
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        // Set creation timestamp
        user.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        // Validation — password null ya empty nahi hona chahiye
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty.");
        }

        // Save user in DB
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
