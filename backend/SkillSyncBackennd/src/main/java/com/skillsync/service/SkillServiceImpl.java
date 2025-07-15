package com.skillsync.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillsync.model.Skill;
import com.skillsync.repository.SkillRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SkillServiceImpl implements SkillService {

	@Autowired
    private SkillRepository skillRepository;

    @Override
    public Skill createSkill(Skill skill) {
        return skillRepository.save(skill);
    }

    @Override
    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    @Override
    public List<Skill> getSkillsByCategory(String category) {
        return skillRepository.findByCategory(category);
    }

    @Override
    public List<Skill> getSkillsByCreator(Long creatorId) {
        return skillRepository.findByCreatorId(creatorId);
    }

    @Override
    public Optional<Skill> getSkillById(Long id) {
        return skillRepository.findById(id);
    }

    @Override
    public void deleteSkill(Long id) {
        skillRepository.deleteById(id);
    }

}
