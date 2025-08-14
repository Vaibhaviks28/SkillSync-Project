package com.skillsync.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

@Entity 
@Table(name = "skills")
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

    @OneToMany(mappedBy = "skill", cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"skill", "user"}) 
    private List<UserSkill> userSkills;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    @JsonIgnoreProperties({"createdSkills", "userSkills", "goals", "feedbacks"}) 
    private User creator;

    @OneToMany(mappedBy = "skill", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("skill") 
    private List<Resource> resources;

    @OneToMany(mappedBy = "skill", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("skill")
    private List<Feedback> feedbacks;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getEstimatedTime() {
		return estimatedTime;
	}

	public void setEstimatedTime(String estimatedTime) {
		this.estimatedTime = estimatedTime;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public List<UserSkill> getUserSkills() {
		return userSkills;
	}

	public void setUserSkills(List<UserSkill> userSkills) {
		this.userSkills = userSkills;
	}

	public List<Resource> getResources() {
		return resources;
	}

	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}

	public List<Feedback> getFeedbacks() {
		return feedbacks;
	}

	public void setFeedbacks(List<Feedback> feedbacks) {
		this.feedbacks = feedbacks;
	}
    
    
}
