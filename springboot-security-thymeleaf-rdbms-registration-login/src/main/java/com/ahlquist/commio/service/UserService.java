package com.ahlquist.commio.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.ahlquist.commio.dto.UserRegistrationDto;
import com.ahlquist.commio.model.User;

public interface UserService extends UserDetailsService {
	User save(UserRegistrationDto registrationDto);
	List<User> getAll();
}
