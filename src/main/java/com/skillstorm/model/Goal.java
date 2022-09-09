package com.skillstorm.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name="Goal")
@Table(name="goal")
public class Goal {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	@NotBlank
	private String name;
	
	@Column(name="description")
	@NotBlank
	private String description;
	
	@Column(name="image_url")
	@NotBlank
	private String imageUrl;
	
	@Column(name="target_date")
	@NotNull
	private LocalDate targetDate;
	
	@Column(name="target_amount")
	@NotNull
	private float targetAmount;
	
	@Column(name="current_amount")
	@NotNull
	private float currentAmount;
	
	@Column(name="username")
	@JsonIgnore
	private String username;

	public Goal() {
		super();
	}

	public Goal(String name, String description, String imageUrl, LocalDate targetDate, float targetAmount,
			float currentAmount, String username) {
		super();
		this.name = name;
		this.description = description;
		this.imageUrl = imageUrl;
		this.targetDate = targetDate;
		this.targetAmount = targetAmount;
		this.currentAmount = currentAmount;
		this.username = username;
	}

	public Goal(int id, String name, String description, String imageUrl, LocalDate targetDate, float targetAmount,
			float currentAmount, String username) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.imageUrl = imageUrl;
		this.targetDate = targetDate;
		this.targetAmount = targetAmount;
		this.currentAmount = currentAmount;
		this.username = username;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public LocalDate getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(LocalDate targetDate) {
		this.targetDate = targetDate;
	}

	public float getTargetAmount() {
		return targetAmount;
	}

	public void setTargetAmount(float targetAmount) {
		this.targetAmount = targetAmount;
	}

	public float getCurrentAmount() {
		return currentAmount;
	}

	public void setCurrentAmount(float currentAmount) {
		this.currentAmount = currentAmount;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "Goal [id=" + id + ", name=" + name + ", description=" + description + ", imageUrl=" + imageUrl
				+ ", targetDate=" + targetDate + ", targetAmount=" + targetAmount + ", currentAmount=" + currentAmount + "]";
	}

}
