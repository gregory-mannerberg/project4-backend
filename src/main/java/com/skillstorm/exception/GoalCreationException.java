package com.skillstorm.exception;

public class GoalCreationException extends RuntimeException {

	private static final long serialVersionUID = -2044355158879077677L;

	public GoalCreationException() {
		super();
	}
	
	public GoalCreationException(int id) {
		super(String.format("Goal with id %d already exists.", id));
	}

}
