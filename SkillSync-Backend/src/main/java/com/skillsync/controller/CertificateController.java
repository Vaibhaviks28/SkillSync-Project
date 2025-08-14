package com.skillsync.controller;

import com.skillsync.model.User;
import com.skillsync.model.Skill;
import com.skillsync.repository.UserRepository;
import com.skillsync.repository.SkillRepository;
import com.skillsync.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/certificate")
public class CertificateController {

    @Autowired
    private CertificateService certificateService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SkillRepository skillRepository;

    @GetMapping("/{userId}/{skillId}")
    public ResponseEntity<Resource> getCertificate(@PathVariable Long userId, @PathVariable Long skillId) {
        User user = userRepository.findById(userId).orElse(null);
        Skill skill = skillRepository.findById(skillId).orElse(null);

        if (user == null || skill == null) {
            return ResponseEntity.notFound().build();
        }
        return certificateService.generateCertificate(user, skill);
    }
}
