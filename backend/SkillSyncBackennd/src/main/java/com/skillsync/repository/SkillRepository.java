package com.skillsync.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skillsync.model.Skill;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {
    List<Skill> findByCategory(String category);
    List<Skill> findByCreatorId(Long creatorId);
}
