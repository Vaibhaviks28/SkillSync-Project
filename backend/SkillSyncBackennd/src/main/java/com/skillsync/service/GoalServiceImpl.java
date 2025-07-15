package com.skillsync.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillsync.model.Goal;
import com.skillsync.repository.GoalRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GoalServiceImpl implements GoalService {

	@Autowired
    private GoalRepository goalRepository;

    @Override
    public Goal createGoal(Goal goal) {
        return goalRepository.save(goal);
    }

    @Override
    public List<Goal> getGoalsByUserId(Long userId) {
        return goalRepository.findByUserId(userId);
    }

    @Override
    public Optional<Goal> getGoalById(Long id) {
        return goalRepository.findById(id);
    }

    @Override
    public void deleteGoal(Long id) {
        goalRepository.deleteById(id);
    }
}

