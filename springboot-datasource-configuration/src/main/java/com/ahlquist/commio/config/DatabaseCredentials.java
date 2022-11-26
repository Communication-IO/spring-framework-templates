package com.ahlquist.commio.config;

public class DatabaseCredentials {

	private String username;
	private String password;

	public DatabaseCredentials() {

	}

	@Override
	public String toString() {
		return "DatabaseCredentials [username=" + username + ", password=" + password + "]";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
