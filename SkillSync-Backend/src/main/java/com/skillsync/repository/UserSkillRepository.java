package com.skillsync.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;

import com.skillsync.model.Skill;
import com.skillsync.model.User;
import com.skillsync.model.UserSkill;

@Repository
public interface UserSkillRepository extends JpaRepository<UserSkill, Long> {

    @Query("SELECT us FROM UserSkill us JOIN FETCH us.skill WHERE us.user.id = :userId")
    List<UserSkill> findByUserIdWithSkill(@Param("userId") Long userId);

    @Query("SELECT us FROM UserSkill us JOIN FETCH us.user WHERE us.skill.id = :skillId")
    List<UserSkill> findBySkillIdWithUser(@Param("skillId") Long skillId);

    List<UserSkill> findByUserId(Long userId);
    List<UserSkill> findBySkillId(Long skillId);
    boolean existsByUserAndSkill(User user, Skill skill);
}
