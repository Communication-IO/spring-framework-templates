package com.ahlquist.commio.model;

import com.ahlquist.commio.aop.Loggable;

public class User {

	private String firstName;
	private String lastName;

	public String getFirstName() {
		return this.firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	@Loggable
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Loggable
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void throwException() {
		throw new RuntimeException("Some Exception");
	}
}