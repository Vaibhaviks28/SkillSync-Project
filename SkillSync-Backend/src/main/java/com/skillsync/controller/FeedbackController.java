package com.skillsync.controller;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.FeedbackDTO;
import com.skillsync.model.Feedback;
import com.skillsync.repository.FeedbackRepository;
import com.skillsync.service.FeedbackService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/feedback")
public class FeedbackController {

	@Autowired
    private FeedbackService feedbackService;
	
	@Autowired
	private FeedbackRepository feedbackRepository;

    @PostMapping
    public ResponseEntity<Feedback> submitFeedback(@RequestBody Feedback feedback) {
        return ResponseEntity.ok(feedbackService.submitFeedback(feedback));
    }
    
    @GetMapping
    public List<FeedbackDTO> getAllFeedbacks() {
        List<Feedback> feedbacks = feedbackRepository.findAll();
        return feedbacks.stream().map(fb -> new FeedbackDTO(
            fb.getId(),
            fb.getUser() != null ? fb.getUser().getName() : "N/A",
            fb.getSkill() != null ? fb.getSkill().getName() : "N/A",
            fb.getGoal() != null ? fb.getGoal().getTitle() : "N/A",
            fb.getMessage(),
            fb.getRating(),
            fb.getSubmittedAt()
        )).collect(Collectors.toList());
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

    @GetMapping("/{id:[0-9]+}")  // Only matches numeric ID
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
