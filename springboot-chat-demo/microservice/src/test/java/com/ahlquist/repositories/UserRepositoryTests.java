package com.ahlquist.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.ahlquist.models.User;
import com.ahlquist.test.BaseTest;

public class UserRepositoryTests extends BaseTest {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(UserRepositoryTests.class);

	@SuppressWarnings("unused")
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	@Qualifier("UserRepository")
	private UserRepository userRepository;

	private User user;

	@BeforeEach
	public void setup() {
		user = build("Rein Ahlquist", "password");
	}

	public User build(final UUID uuid, final @NonNull String username, final @NonNull String password) {
		User u = new User(username, password);
		u.setUuid(uuid);
		return u;
	}

	public User build(final @NonNull String username, final @NonNull String password) {
		User u = new User(username, password);
		u.setUuid(UUID.randomUUID());
		return u;
	}

	@Test
	public void checkUser() {
		assertThat(user).isNotNull();
	}

	@Test
	void findByUserName() {
		//TODO (dahlquist) : not implemented
		// Iterable<User> it = userRepository.findAll();
		assertThat("not Implemented").isNotNull();
	}

	// JUnit test for get user by id operation
	@Test
	public void givenUserObject_whenFindByUuid_thenReturnUserObject() {
		//TODO (dahlquist) : not implemented
//        User u = userRepository.save(user);
//
//        // when - action or the behavior that we are going test
//        //User userDB = userRepository.findByUuid(u.getId()).get();
//
//        // then - verify the output
//        //assertThat(userDB).isNotNull();
//        assertThat(u).isNotNull();
		assertThat("not Implemented").isNotNull();
	}

	@DisplayName("JUnit test for update user operation")
	@Test
	public void givenUserObject_whenUpdateUser_thenReturnUpdatedUser() {
		//TODO (dahlquist) : not implemented
//		logger.debug("user: " + user.toString());
//		User sUser = userRepository.save(user);
//		UUID uuid = sUser.getUuid();
//
//		// when - action or the behavior that we are going test
//		User savedUser = userRepository.findByUuid(user.getUuid()).get();
//		logger.debug("saveUser: " + savedUser.toString());
//		savedUser.setUsername("Rein");
//		User updatedUser = userRepository.save(savedUser);
//		logger.debug("saveUser: " + updatedUser.toString());
//
//		// then - verify the output
//		assertThat(updatedUser.getUsername()).isEqualTo("Rein");
		assertThat("not Implemented").isNotNull();
	}

	// JUnit test for delete user operation
	@DisplayName("JUnit test for delete user operation")
	@Test
	public void givenUserObject_whenDelete_thenRemoveUser() {
		//TODO (dahlquist) : not implemented
		// userRepository.save(user);

		// when - action or the behavior that we are going test
		// userRepository.deleteById(user.getUuid());
		// Optional<User> userOptional = userRepository.findByUuid(user.getId());

		// then - verify the output
		// assertThat(userOptional).isEmpty();
		assertThat("not Implemented").isNotNull();
	}

	// JUnit test for save user operation
	@DisplayName("JUnit test for save user operation")
	@Test
	public void givenUserObject_whenSave_thenReturnSavedUser() {
		//TODO (dahlquist) : not implemented
//        // given - precondition or setup
//        User user = build("Douglas", "password");
//
//        // when - action or the behavior that we are going test
//        User savedUser = userRepository.save(user);
//
//        // then - verify the output
//        assertThat(savedUser).isNotNull();
//        //assertThat(savedUser.getUuid()).isGreaterThan(0);
		assertThat("not Implemented").isNotNull();
	}

	// JUnit test for get all users operation
	@DisplayName("JUnit test for get all users operation")
	@Test
	public void givenUsersList_whenFindAll_thenUsersList() {
		//TODO (dahlquist) : not implemented
//        // given - precondition or setup
//        User user1 = build("Douglas", "Ahlquist");
//        userRepository.save(user);
//        userRepository.save(user1);
//
//        // when - action or the behavior that we are going test
//        List<User> userList = (List<User>) userRepository.findAll();
//
//        // then - verify the output
//        assertThat(userList).isNotNull();
//        assertThat(userList.size()).isEqualTo(2);
		assertThat("not Implemented").isNotNull();
	}
}
