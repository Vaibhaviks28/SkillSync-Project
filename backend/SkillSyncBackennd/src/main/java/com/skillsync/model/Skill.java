package com.skillsync.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "skills")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String category;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String level;
    private String estimatedTime;

    @ManyToOne
    @JoinColumn(name = "creator_id", nullable = false)
    private User creator;

    @OneToMany(mappedBy = "skill", cascade = CascadeType.ALL)
    private List<UserSkill> userSkills;

    @OneToMany(mappedBy = "skill", cascade = CascadeType.ALL)
    private List<Resource> resources;

    @OneToMany(mappedBy = "skill", cascade = CascadeType.ALL)
    private List<Feedback> feedbacks;
}
