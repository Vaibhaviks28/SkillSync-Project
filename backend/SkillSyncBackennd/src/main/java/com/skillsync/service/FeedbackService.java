package com.skillsync.service;

import java.util.List;
import java.util.Optional;

import com.skillsync.model.Feedback;

public interface FeedbackService {
    Feedback submitFeedback(Feedback feedback);
    List<Feedback> getFeedbackByUserId(Long userId);
    List<Feedback> getFeedbackByGoalId(Long goalId);
    List<Feedback> getFeedbackBySkillId(Long skillId);
    Optional<Feedback> getFeedbackById(Long id);
    void deleteFeedback(Long id);
}
