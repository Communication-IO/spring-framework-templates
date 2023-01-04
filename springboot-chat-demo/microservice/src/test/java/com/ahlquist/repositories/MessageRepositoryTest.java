package com.ahlquist.repositories;

import java.util.UUID;
import static org.assertj.core.api.Assertions.assertThat;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.ahlquist.models.Message;
import com.ahlquist.models.User;
import com.ahlquist.test.BaseTest;

public class MessageRepositoryTest extends BaseTest{

	@Autowired
	@Qualifier("UserRepository")
	private UserRepository userRepository;
	
	@Autowired
	@Qualifier("MessageRepository")
	private MessageRepository messageRepository;
	
	@SuppressWarnings("unused")
	private User user;
	@SuppressWarnings("unused")
	private Message message;

	@BeforeEach
	public void setup() {
		user = build(UUID.randomUUID(), "Rein Ahlquist", "password");
		
	}
	
	public User build(final UUID uuid, final @NonNull String username, final @NonNull String password) {
		User u = new User(username, password);
		u.setUuid(uuid);
		return u;
	}
	
	@Test
	public void findById() {
		//TODO (dahlquist) : not implemented
		assertThat("not Implemented").isNotNull();
	}
	
	@Test
	public void findByUuidAndBetweenTimestamp() {
		//TODO (dahlquist) : not implemented
		assertThat("not Implemented").isNotNull();
	}
	
	@Test
	public void findSendersOrRecipientMesssageList() {
		//TODO (dahlquist) : not implemented
		assertThat("not Implemented").isNotNull();
	}

	@Test
	public void findRecipietsMesssageList() {
		//TODO (dahlquist) : not implemented
		assertThat("not Implemented").isNotNull();
	}
}
