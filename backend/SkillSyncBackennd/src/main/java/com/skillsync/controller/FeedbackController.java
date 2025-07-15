package com.skillsync.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillsync.model.Feedback;
import com.skillsync.service.FeedbackService;

import lombok.RequiredArgsConstructor;
@RestController
@RequestMapping("/api/feedback")
@RequiredArgsConstructor
public class FeedbackController {

	@Autowired
    private FeedbackService feedbackService;

    @PostMapping
    public ResponseEntity<Feedback> submitFeedback(@RequestBody Feedback feedback) {
        return ResponseEntity.ok(feedbackService.submitFeedback(feedback));
    }

    @GetMapping("/user/{userId}")
    public List<Feedback> getByUser(@PathVariable Long userId) {
        return feedbackService.getFeedbackByUserId(userId);
    }

    @GetMapping("/goal/{goalId}")
    public List<Feedback> getByGoal(@PathVariable Long goalId) {
        return feedbackService.getFeedbackByGoalId(goalId);
    }

    @GetMapping("/skill/{skillId}")
    public List<Feedback> getBySkill(@PathVariable Long skillId) {
        return feedbackService.getFeedbackBySkillId(skillId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Feedback> getFeedbackById(@PathVariable Long id) {
        return feedbackService.getFeedbackById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeedback(@PathVariable Long id) {
        feedbackService.deleteFeedback(id);
        return ResponseEntity.noContent().build();
    }
}
