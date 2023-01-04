package com.ahlquist.models;

import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity(name = "User")
@Table(name = "user")
public class User {

	// Ids should be changed to a UUIDs for security purposes, so that the IDs are
	// simply not a sequence of number from 1 to 2,14 Billion,
	// The is a security risk. Using an approach found at:
	// https://stackoverflow.com/questions/66936394/uuid-primary-key-for-jpa-entity-safe-approach-to-use-unique-values-on-multiple
	// and
	// https://stackoverflow.com/questions/61969707/spring-boot-locking-code-to-get-an-unique-id
	// maybe a good end run around this security issue

	// See https://en.wikipedia.org/wiki/Universally_unique_identifier#Collisions
	// for details on collisions

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "uuid", nullable = false, unique = true, length=36)
	@Type(type = "uuid-char")
	private UUID uuid = UUID.randomUUID();

	@Column(name = "token", nullable = true, length = 90)
	String token;

	@Column(name = "username", nullable = false, length = 64)
	private String username;

	@Column(name = "password", nullable = false, length = 32)
	private String password;

	public User() {
	}

	public User(@NonNull String username, @NonNull String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public User(@Nullable UUID uuid, @NonNull String username, @NonNull String password) {
		super();
		this.uuid = uuid;
		this.username = username;
		this.password = password;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(uuid, other.uuid) && Objects.equals(password, other.password)
				&& Objects.equals(token, other.token) && Objects.equals(username, other.username);
	}

	public UUID getUuid() {
		return this.uuid;
	}

	public void setUuid(@NonNull UUID uuid) {
		this.uuid = uuid;
	}

	public String getPassword() {
		return this.password;
	}

	public String getUsername() {
		return this.username;
	}

	@Override
	public int hashCode() {
		return Objects.hash(uuid, password, token, username);
	}

	public void setPassword(@NonNull String password) {
		this.password = password;
	}

	public void setUsername(@NonNull String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "User [uuid=" + uuid + ", token=" + token + ", username=" + username + ", password=" + password + "]";
	}

}
