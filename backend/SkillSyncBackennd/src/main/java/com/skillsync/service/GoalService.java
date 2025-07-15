package com.skillsync.service;

import java.util.List;
import java.util.Optional;

import com.skillsync.model.Goal;

public interface GoalService {
    Goal createGoal(Goal goal);
    List<Goal> getGoalsByUserId(Long userId);
    Optional<Goal> getGoalById(Long id);
    void deleteGoal(Long id);
}
