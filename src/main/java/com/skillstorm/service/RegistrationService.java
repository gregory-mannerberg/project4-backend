package com.skillstorm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.skillstorm.dto.UserDto;
import com.skillstorm.model.Authority;
import com.skillstorm.model.User;
import com.skillstorm.repository.AuthorityRepository;
import com.skillstorm.repository.UserRepository;

@Service
public class RegistrationService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AuthorityRepository authorityRepository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	public void register(UserDto userDto) {
		User user = new User(userDto.getUsername(), encoder.encode(userDto.getPassword()), true);
		userRepository.save(user);
		Authority authority = new Authority(userDto.getUsername(), "ROLE_USER");
		authorityRepository.save(authority);
	}

}
