package com.skillsync.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillsync.model.UserSkill;
import com.skillsync.repository.UserSkillRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserSkillServiceImpl implements UserSkillService {

	@Autowired
    private  UserSkillRepository userSkillRepository;

    @Override
    public UserSkill assignSkillToUser(UserSkill userSkill) {
        return userSkillRepository.save(userSkill);
    }

    @Override
    public List<UserSkill> getSkillsOfUser(Long userId) {
        return userSkillRepository.findByUserId(userId);
    }

    @Override
    public List<UserSkill> getUsersOfSkill(Long skillId) {
        return userSkillRepository.findBySkillId(skillId);
    }
}

