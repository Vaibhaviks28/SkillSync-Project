package com.skillsync.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillsync.model.UserSkill;
import com.skillsync.service.UserSkillService;

import lombok.RequiredArgsConstructor;
@RestController
@RequestMapping("/api/user-skills")
@RequiredArgsConstructor
public class UserSkillController {

	@Autowired
    private UserSkillService userSkillService;

    @PostMapping
    public ResponseEntity<UserSkill> assignSkill(@RequestBody UserSkill userSkill) {
        return ResponseEntity.ok(userSkillService.assignSkillToUser(userSkill));
    }

    @GetMapping("/user/{userId}")
    public List<UserSkill> getSkillsOfUser(@PathVariable Long userId) {
        return userSkillService.getSkillsOfUser(userId);
    }

    @GetMapping("/skill/{skillId}")
    public List<UserSkill> getUsersOfSkill(@PathVariable Long skillId) {
        return userSkillService.getUsersOfSkill(skillId);
    }
}
