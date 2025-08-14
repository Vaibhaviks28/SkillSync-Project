package com.skillsync.model;

public class UserSkillRequest {
    private Long userId;
    private Long skillId;

    public UserSkillRequest() {}

    public UserSkillRequest(Long userId, Long skillId) {
        this.userId = userId;
        this.skillId = skillId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSkillId() {
        return skillId;
    }

    public void setSkillId(Long skillId) {
        this.skillId = skillId;
    }
}