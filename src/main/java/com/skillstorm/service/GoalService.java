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
	
	public Optional<Goal> findById(int id) {
		return goalRepository.findById(id);
	}
	
	@Transactional
	public Goal createGoal(Goal goal) {
		Optional<Goal> dbGoal = goalRepository.findById(goal.getId());
		if (dbGoal.isPresent()) {
			throw new GoalCreationException(goal.getId());
		}
		else {
			return goalRepository.save(goal);
		}
	}
	
	@Transactional
	public Optional<Goal> updateGoal(Goal goal) {
		Optional<Goal> dbGoal = goalRepository.findById(goal.getId());
		if (dbGoal.isPresent()) {
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
