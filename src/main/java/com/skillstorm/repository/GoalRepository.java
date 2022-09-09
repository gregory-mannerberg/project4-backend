package com.skillstorm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.skillstorm.model.Goal;

@Repository
public interface GoalRepository extends CrudRepository<Goal, Integer>{
	
	public List<Goal> findByUsername(String username);
	
	public Optional<Goal> findByIdAndUsername(int id, String username);

}
