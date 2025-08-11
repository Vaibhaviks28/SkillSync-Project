package com.skillsync.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillsync.model.Goal;
import com.skillsync.repository.GoalRepository;
import com.skillsync.repository.UserRepository;


@Service
public class GoalServiceImpl implements GoalService {

	@Autowired
    private GoalRepository goalRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public Goal createGoal(Goal goal) {
	    // Fetch full user to avoid nulls
	    Long userId = goal.getUser().getId();
	    goal.setUser(
	        userRepository.findById(userId)
	            .orElseThrow(() -> new RuntimeException("User not found with id: " + userId))
	    );
	    
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

