package com.skillsync.model;

import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity // Ye batata hai ki ye class ek DB entity hai
@Table(name = "users") // Table ka naam "users" hoga DB me
public class User {

    @Id // Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto increment ID
    private Long id;

    private String name; // User ka naam
    private String email; // User ka email

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) 
    // Password sirf request ke time accept hoga, response me kabhi bhejenge nahi (security)
    private String password;

    private String role; // e.g., "admin" ya "user"

    @JsonProperty("profilePicture")
    // JSON me property ka naam "profilePicture" hoga
    private String profilePicture;

    private Timestamp createdAt; // Account creation time

    @OneToMany(mappedBy = "creator") 
    @JsonIgnoreProperties("creator") 
    // Ye mapping Skills table se hai — ek user ne bohot saare skills banaye ho sakte hain
    // "creator" field Skill entity me foreign key store karegi
    private List<Skill> createdSkills;

    @OneToMany(mappedBy = "user") 
    @JsonIgnoreProperties("user") 
    // Ye mapping UserSkill table se hai — ek user ke multiple skill connections ho sakte hain
    private List<UserSkill> userSkills;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL) 
    @JsonIgnoreProperties("user") 
    // Ye mapping Goal table se hai — ek user ke multiple goals ho sakte hain
    // Cascade = agar user delete ho jaye toh uske goals bhi delete ho jaye
    private List<Goal> goals;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL) 
    @JsonIgnoreProperties("user") 
    // Ye mapping Feedback table se hai — user multiple feedback de sakta hai
    private List<Feedback> feedbacks;

    // --------- Getters & Setters ----------
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getProfilePicture() { return profilePicture; }
    public void setProfilePicture(String profilePicture) { this.profilePicture = profilePicture; }

    public List<Skill> getCreatedSkills() { return createdSkills; }
    public void setCreatedSkills(List<Skill> createdSkills) { this.createdSkills = createdSkills; }

    public List<UserSkill> getUserSkills() { return userSkills; }
    public void setUserSkills(List<UserSkill> userSkills) { this.userSkills = userSkills; }

    public List<Goal> getGoals() { return goals; }
    public void setGoals(List<Goal> goals) { this.goals = goals; }

    public List<Feedback> getFeedbacks() { return feedbacks; }
    public void setFeedbacks(List<Feedback> feedbacks) { this.feedbacks = feedbacks; }

    public Timestamp getCreatedAt() { return createdAt; }
}
