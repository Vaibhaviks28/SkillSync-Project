package com.skillsync.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillsync.model.Skill;
import com.skillsync.model.User;
import com.skillsync.model.UserSkill;
import com.skillsync.repository.SkillRepository;
import com.skillsync.repository.UserRepository;
import com.skillsync.repository.UserSkillRepository;

@Service
public class UserSkillServiceImpl implements UserSkillService {

    @Autowired
    private UserSkillRepository userSkillRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Override
    public UserSkill assignSkillToUser(UserSkill userSkill) {
        Long userId = userSkill.getUser().getId();
        Long skillId = userSkill.getSkill().getId();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        Skill skill = skillRepository.findById(skillId)
                .orElseThrow(() -> new RuntimeException("Skill not found with id: " + skillId));

        if (userSkillRepository.existsByUserAndSkill(user, skill)) {
            throw new RuntimeException("Skill already added to this user.");
        }

        userSkill.setUser(user);
        userSkill.setSkill(skill);

        return userSkillRepository.save(userSkill);
    }

    @Override
    public List<UserSkill> getSkillsOfUser(Long userId) {
        return userSkillRepository.findByUserIdWithSkill(userId);  // updated to load full Skill info
    }

    @Override
    public List<UserSkill> getUsersOfSkill(Long skillId) {
        return userSkillRepository.findBySkillId(skillId);
    }
}
