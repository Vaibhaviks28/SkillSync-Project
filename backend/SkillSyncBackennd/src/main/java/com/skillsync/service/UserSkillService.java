package com.skillsync.service;

import java.util.List;

import com.skillsync.model.UserSkill;


public interface UserSkillService {
    UserSkill assignSkillToUser(UserSkill userSkill);
    List<UserSkill> getSkillsOfUser(Long userId);
    List<UserSkill> getUsersOfSkill(Long skillId);
}
