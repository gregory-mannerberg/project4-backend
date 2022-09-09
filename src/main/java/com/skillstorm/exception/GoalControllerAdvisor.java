package com.skillstorm.exception;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GoalControllerAdvisor extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(GoalCreationException.class)
	public ResponseEntity<Object> handleGoalCreationException(
			GoalCreationException e, WebRequest request) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("status", HttpStatus.CONFLICT.value());
		body.put("error", HttpStatus.CONFLICT.getReasonPhrase());
		body.put("message", "Goal already exists");
		return new ResponseEntity<>(body, HttpStatus.CONFLICT);
	}

}
