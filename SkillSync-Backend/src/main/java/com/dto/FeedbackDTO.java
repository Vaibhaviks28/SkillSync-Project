package com.dto;


import java.sql.Timestamp;

public class FeedbackDTO {
 private Long id;
 private String userName;
 private String skillName;
 private String goalTitle;
 private String message;
 private Integer rating;
 private Timestamp submittedAt;

 public FeedbackDTO(Long id, String userName, String skillName, String goalTitle,
                    String message, Integer rating, Timestamp submittedAt) {
     this.id = id;
     
     
     this.userName = userName;
     this.skillName = skillName;
     this.goalTitle = goalTitle;
     this.message = message;
     this.rating = rating;
     this.submittedAt = submittedAt;
 }

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getUserName() {
	return userName;
}

public void setUserName(String userName) {
	this.userName = userName;
}

public String getSkillName() {
	return skillName;
}

public void setSkillName(String skillName) {
	this.skillName = skillName;
}

public String getGoalTitle() {
	return goalTitle;
}

public void setGoalTitle(String goalTitle) {
	this.goalTitle = goalTitle;
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

public void setSubmittedAt(Timestamp submittedAt) {
	this.submittedAt = submittedAt;
}

 
}

