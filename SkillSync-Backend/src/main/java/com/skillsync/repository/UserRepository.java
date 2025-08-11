package com.skillsync.repository;

import com.skillsync.model.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // JpaRepository<User, Long> ka matlab:
    // User -> kis entity ke liye ye repository hai
    // Long -> primary key ka type

    // Optional ka matlab: value ho bhi sakti hai ya null bhi ho sakti hai
    Optional<User> findByEmail(String email);
    // Ye method automatically SQL query banata hai:
    // SELECT * FROM users WHERE email = ? 
    // aur result ko Optional<User> me return karega

    Optional<User> findByEmailAndPassword(String email, String password);
    // Ye method banayega query:
    // SELECT * FROM users WHERE email = ? AND password = ?
    // Agar match mila toh user return karega
}
