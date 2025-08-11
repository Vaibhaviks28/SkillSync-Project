package com.skillsync.model;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "feedback")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnoreProperties({"feedbacks", "goals", "createdSkills", "userSkills"})
    private User user;

    @ManyToOne
    @JoinColumn(name = "goal_id")
    @JsonIgnoreProperties("feedbacks")
    private Goal goal;

    @ManyToOne
    @JoinColumn(name = "skill_id")
    @JsonIgnoreProperties("feedbacks")
    private Skill skill;

    @Column(columnDefinition = "TEXT")
    private String message;

    private Integer rating;
    private Timestamp submittedAt;
    public void setSubmittedAt(Timestamp submittedAt) {
        this.submittedAt = submittedAt;
    }
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
	public Goal getGoal() {
		return goal;
	}
	public void setGoal(Goal goal) {
		this.goal = goal;
	}
	public Skill getSkill() {
		return skill;
	}
	public void setSkill(Skill skill) {
		this.skill = skill;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Integer getRating() {
		return rating;
	}
	public void setRating(Integer rating) {
		this.rating = rating;
	}
	public Timestamp getSubmittedAt() {
		return submittedAt;
	}
	public Feedback() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Feedback(Long id, User user, Goal goal, Skill skill, String message, Integer rating, Timestamp submittedAt) {
		super();
		this.id = id;
		this.user = user;
		this.goal = goal;
		this.skill = skill;
		this.message = message;
		this.rating = rating;
		this.submittedAt = submittedAt;
	}

}
