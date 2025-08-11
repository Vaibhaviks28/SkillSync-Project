package com.skillsync.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dto.SkillDTO;
import com.skillsync.model.Skill;
import com.skillsync.service.SkillService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/skills")
public class SkillController {

    @Autowired
    private SkillService skillService;

    @PostMapping("/add")
    public ResponseEntity<Skill> createSkill(@RequestBody Skill skill) {
        Skill createdSkill = skillService.createSkill(skill);
        return ResponseEntity.ok(createdSkill);
    }

    @GetMapping
    public List<SkillDTO> getAllSkills() {
        List<Skill> skills = skillService.getAllSkills();
        return skills.stream().map(SkillDTO::new).collect(Collectors.toList());
    }
    

    @GetMapping("/category/{category}")
    public List<Skill> getSkillsByCategory(@PathVariable String category) {
        return skillService.getSkillsByCategory(category);
    }

    @GetMapping("/creator/{creatorId}")
    public List<Skill> getSkillsByCreator(@PathVariable("creatorId") Long creatorId) {
        return skillService.getSkillsByCreator(creatorId);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Skill> getSkillById(@PathVariable("id") Long id) {
        return skillService.getSkillById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deleteSkill(@PathVariable Long id) {
        skillService.deleteSkill(id);
        return ResponseEntity.noContent().build();
    }
}
