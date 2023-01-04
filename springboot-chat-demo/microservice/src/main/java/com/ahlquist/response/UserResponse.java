package com.ahlquist.response;

import java.util.UUID;

import org.checkerframework.checker.nullness.qual.NonNull;

public class UserResponse {

	UUID uuid;
	String token;

	public UserResponse() {
		super();
	}

	public UserResponse(@NonNull UUID uuid, @NonNull String token) {
		super();
		this.uuid = uuid;
		this.token = token;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(@NonNull UUID uuid) {
		this.uuid = uuid;
	}

	public String getToken() {
		return token;
	}

	public void setToken(@NonNull String token) {
		this.token = token;
	}
}
