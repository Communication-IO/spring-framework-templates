package com.ahlquist.response;

import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * { "username": "string", "password": "pa$$word" }
 * 
 * @author douglasahlquist
 */
public class LoginResponse {

	String username;
	String password;

	public LoginResponse() {
		super();
	}

	public LoginResponse(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(@NonNull String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(@NonNull String password) {
		this.password = password;
	}
}
