package com.skillstorm;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.skillstorm.model.Goal;
import com.skillstorm.repository.GoalRepository;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DirtiesContext(classMode=ClassMode.BEFORE_EACH_TEST_METHOD)
public class GoalTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private GoalRepository goalRepository;
	
	private Goal dbGoal;
	
	private Goal goal;
	
	@BeforeEach
	public void init() {
		dbGoal = new Goal();
		dbGoal.setName("House");
		dbGoal.setDescription("Own a home");
		dbGoal.setImageUrl("house picture");
		dbGoal.setTargetDate(LocalDate.of(2023, 10, 1));
		dbGoal.setTargetAmount(200_000);
		dbGoal.setCurrentAmount(150_000);
		dbGoal.setUsername("test");
		goalRepository.save(dbGoal);
		goal = new Goal();
		goal.setName("Car");
		goal.setDescription("Purchase a vehicle");
		goal.setImageUrl("car picture");
		goal.setTargetDate(LocalDate.of(2023, 1, 14));
		goal.setTargetAmount(25_000);
		goal.setCurrentAmount(18_000);
		goal.setUsername("test");
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////
	/*
	 * GET Tests
	 */
	///////////////////////////////////////////////////////////////////////////////////////////
	
	@Test
	void getByIdFound() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders
				.get("/goals/id/1")
				.with(SecurityMockMvcRequestPostProcessors.user("test")))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").value(dbGoal.getName()))
				.andExpect(MockMvcResultMatchers.jsonPath("$.description").value(dbGoal.getDescription()))
				.andExpect(MockMvcResultMatchers.jsonPath("$.imageUrl").value(dbGoal.getImageUrl()))
				.andExpect(MockMvcResultMatchers.jsonPath("$.targetDate").value(dbGoal.getTargetDate().toString()))
				.andExpect(MockMvcResultMatchers.jsonPath("$.targetAmount").value(dbGoal.getTargetAmount()))
				.andExpect(MockMvcResultMatchers.jsonPath("$.currentAmount").value(dbGoal.getCurrentAmount()));
	}
	
	@Test
	void getByIdNotFound() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders
				.get("/goals/id/-1")
				.with(SecurityMockMvcRequestPostProcessors.user("test")))
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}
	
	@Test
	void getByIdWrongUser() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders
				.get("/goals/id/1")
				.with(SecurityMockMvcRequestPostProcessors.user("exam")))
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}
	
	@Test
	void getAllByUsername() throws Exception {
		goalRepository.save(goal);
		this.mockMvc.perform(MockMvcRequestBuilders
				.get("/goals")
				.with(SecurityMockMvcRequestPostProcessors.user("test")))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value(dbGoal.getName()))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].description").value(dbGoal.getDescription()))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].imageUrl").value(dbGoal.getImageUrl()))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].targetDate").value(dbGoal.getTargetDate().toString()))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].targetAmount").value(dbGoal.getTargetAmount()))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].currentAmount").value(dbGoal.getCurrentAmount()))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value(goal.getName()))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].description").value(goal.getDescription()))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].imageUrl").value(goal.getImageUrl()))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].targetDate").value(goal.getTargetDate().toString()))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].targetAmount").value(goal.getTargetAmount()))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].currentAmount").value(goal.getCurrentAmount()));
	}
	
	@Test
	void getAllByUsernameWithOtherUserData() throws Exception {
		goal.setUsername("exam");
		goalRepository.save(goal);
		this.mockMvc.perform(MockMvcRequestBuilders
				.get("/goals")
				.with(SecurityMockMvcRequestPostProcessors.user("test")))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(1));
	}

}
