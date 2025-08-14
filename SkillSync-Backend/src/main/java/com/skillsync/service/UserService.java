package com.skillsync.service;

import java.util.List;
import java.util.Optional;

import com.skillsync.model.User;

public interface UserService {
    User createUser(User user);
    Optional<User> getUserById(Long id);
    List<User> getAllUsers();
    Optional<User> getUserByEmail(String email);
    void deleteUser(Long id);
}
