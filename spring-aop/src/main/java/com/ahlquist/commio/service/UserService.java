package com.ahlquist.commio.service;

import org.springframework.stereotype.Service;

import com.ahlquist.commio.model.User;

@Service("userService")
public class UserService {

	private User user;

	public User getUser() {
		return this.user;
	}

	public void setUser(User u) {
	}
}