package com.skillsync.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillsync.model.Feedback;
import com.skillsync.repository.FeedbackRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {

	@Autowired
    private FeedbackRepository feedbackRepository;

    @Override
    public Feedback submitFeedback(Feedback feedback) {
        feedback.setSubmittedAt(new Timestamp(System.currentTimeMillis()));
        return feedbackRepository.save(feedback);
    }

    @Override
    public List<Feedback> getFeedbackByUserId(Long userId) {
        return feedbackRepository.findByUserId(userId);
    }

    @Override
    public List<Feedback> getFeedbackByGoalId(Long goalId) {
        return feedbackRepository.findByGoalId(goalId);
    }

    @Override
    public List<Feedback> getFeedbackBySkillId(Long skillId) {
        return feedbackRepository.findBySkillId(skillId);
    }

    @Override
    public Optional<Feedback> getFeedbackById(Long id) {
        return feedbackRepository.findById(id);
    }

    @Override
    public void deleteFeedback(Long id) {
        feedbackRepository.deleteById(id);
    }
}
