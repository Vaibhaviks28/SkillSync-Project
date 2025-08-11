package com.skillsync.model;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_skills")
public class UserSkill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnoreProperties({"userSkills", "createdSkills", "goals", "feedbacks"})
    private User user;

    @ManyToOne
    @JoinColumn(name = "skill_id", nullable = false)
    @JsonIgnoreProperties({"userSkills", "resources", "feedbacks", "creator"})
    private Skill skill;


    private String status;
    private Float progressPercentage;
    private Timestamp startedAt;
    private Timestamp completedAt;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Skill getSkill() {
		return skill;
	}
	public void setSkill(Skill skill) {
		this.skill = skill;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Float getProgressPercentage() {
		return progressPercentage;
	}
	public void setProgressPercentage(Float progressPercentage) {
		this.progressPercentage = progressPercentage;
	}
	public Timestamp getStartedAt() {
		return startedAt;
	}
	public void setStartedAt(Timestamp startedAt) {
		this.startedAt = startedAt;
	}
	public Timestamp getCompletedAt() {
		return completedAt;
	}
	public void setCompletedAt(Timestamp completedAt) {
		this.completedAt = completedAt;
	}
    
    
}
