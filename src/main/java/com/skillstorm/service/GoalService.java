package com.skillstorm.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.exception.GoalCreationException;
import com.skillstorm.model.Goal;
import com.skillstorm.repository.GoalRepository;

@Service
public class GoalService {
	
	@Autowired
	private GoalRepository goalRepository;
	
	public List<Goal> findAllByUsername(String username) {
		return goalRepository.findByUsername(username);
	}
	
	public Optional<Goal> findById(int id, String username) {
		return goalRepository.findByIdAndUsername(id, username);
	}
	
	@Transactional
	public Goal createGoal(Goal goal, String username) {
		Optional<Goal> dbGoal = goalRepository.findByIdAndUsername(goal.getId(), username);
		if (dbGoal.isPresent()) {
			throw new GoalCreationException(goal.getId());
		}
		else {
			goal.setUsername(username);
			return goalRepository.save(goal);
		}
	}
	
	@Transactional
	public Optional<Goal> updateGoal(Goal goal, String username) {
		Optional<Goal> dbGoal = goalRepository.findByIdAndUsername(goal.getId(), username);
		if (dbGoal.isPresent()) {
			goal.setUsername(username);
			return Optional.of(goalRepository.save(goal));
		}
		else {
			return Optional.empty();
		}
		
	}
	
	public void deleteById(int id) {
		goalRepository.deleteById(id);
	}

}
