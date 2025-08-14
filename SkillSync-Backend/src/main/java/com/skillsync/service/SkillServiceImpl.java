package com.skillsync.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.skillsync.exception.DuplicateEntryException;
import com.skillsync.model.Skill;
import com.skillsync.repository.SkillRepository;

@Service
public class SkillServiceImpl implements SkillService {

    @Autowired
    private SkillRepository skillRepository;

    @Override
    public Skill createSkill(Skill skill) {
        if (existsByNameAndCategory(skill.getName(), skill.getCategory())) {
            throw new DuplicateEntryException("Skill with the same name and category already exists.");
        }
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

    // IMPLEMENT THIS
    @Override
    public boolean existsByNameAndCategory(String name, String category) {
        return skillRepository.existsByNameAndCategory(name, category);
    }
}