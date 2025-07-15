package com.skillsync.model;

import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;
    private String role;
    @JsonProperty("profilePicture")
    private String profilePicture;
    private Timestamp createdAt;

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL)
    private List<Skill> createdSkills;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserSkill> userSkills;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Goal> goals;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Feedback> feedbacks;
}
