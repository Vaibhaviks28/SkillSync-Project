package com.skillsync.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillsync.model.UserSkill;
import com.skillsync.service.UserSkillService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/user-skills")
public class UserSkillController {

	@Autowired
    private UserSkillService userSkillService;

	@PostMapping
	public ResponseEntity<UserSkill> assignSkillToUser(@RequestBody UserSkill userSkill) {
	    UserSkill created = userSkillService.assignSkillToUser(userSkill);
	    return ResponseEntity.status(HttpStatus.CREATED).body(created);
	}


    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserSkill>> getSkillsOfUser(@PathVariable Long userId) {
        List<UserSkill> userSkills = userSkillService.getSkillsOfUser(userId);
        return ResponseEntity.ok(userSkills);
    }

    @GetMapping("/skill/{skillId}")
    public ResponseEntity<List<UserSkill>> getUsersOfSkill(@PathVariable Long skillId) {
        return ResponseEntity.ok(userSkillService.getUsersOfSkill(skillId));
    }
}