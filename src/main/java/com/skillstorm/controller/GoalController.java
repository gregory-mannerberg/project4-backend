package com.skillstorm.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.model.Goal;
import com.skillstorm.service.GoalService;

@RestController
@RequestMapping("/goals")
public class GoalController {
	
	@Autowired
	private GoalService goalService;
	
	@GetMapping
	public ResponseEntity<List<Goal>> findAllByUsername(Authentication auth) {
		return ResponseEntity.ok(goalService.findAllByUsername(auth.getName()));
	}
	
	@GetMapping("/id/{id}")	
	public ResponseEntity<Goal> findById(@PathVariable int id, Authentication auth) {
		return ResponseEntity.of(goalService.findById(id, auth.getName()));
	}
	
	@PostMapping
	public ResponseEntity<Goal> createGoal(@Valid @RequestBody Goal goal, Authentication auth) {
		return ResponseEntity.ok(goalService.createGoal(goal, auth.getName()));
	}
	
	@PutMapping
	public ResponseEntity<Goal> updateGoal(@Valid @RequestBody Goal goal, Authentication auth) {
		return ResponseEntity.of(goalService.updateGoal(goal, auth.getName()));
	}
	
	@DeleteMapping("/id/{id}")
	public ResponseEntity<?> deleteById(@PathVariable int id) {
		return ResponseEntity.ok().build();
	}

}
