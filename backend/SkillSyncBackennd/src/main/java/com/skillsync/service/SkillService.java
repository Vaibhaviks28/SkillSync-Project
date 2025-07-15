package com.skillsync.service;

import java.util.List;
import java.util.Optional;

import com.skillsync.model.Skill;

public interface SkillService {
    Skill createSkill(Skill skill);
    List<Skill> getAllSkills();
    List<Skill> getSkillsByCategory(String category);
    List<Skill> getSkillsByCreator(Long creatorId);
    Optional<Skill> getSkillById(Long id);
    void deleteSkill(Long id);
}

