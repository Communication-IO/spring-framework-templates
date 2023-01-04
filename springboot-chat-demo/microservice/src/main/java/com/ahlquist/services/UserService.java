package com.ahlquist.services;

import java.util.Optional;
import java.util.UUID;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ahlquist.exception.BadRequestException;
import com.ahlquist.models.User;
import com.ahlquist.repositories.UserRepository;

@Component(value = "UserService")
@Transactional
public class UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	@Qualifier(value = "UserRepository")
	UserRepository userRepository;

	public User create(final @NonNull User user) {
		User u = userRepository.save(user);
		return u;
	}

	public Optional<User> exists(String username) {
		return userRepository.findByUsername(username);
	}

	public boolean exists(final UUID uuid) {
		return userRepository.existsById(uuid);
	}
	
	public Optional<User> findByToken(final @NonNull String token) {
		return userRepository.findByToken(token);
	}

	public Optional<User> findByUuid(final @NonNull UUID uuid) {
		logger.debug("uuid: " + uuid.toString());
		return userRepository.findByUuid(uuid);
	}

	public Optional<User> findByUsername(final @NonNull String username) {
		return userRepository.findByUsername(username);
	}

	public Optional<User> findByUuidAndToken(final @NonNull UUID uuid, final @NonNull String token) {
		return userRepository.findByUuidAndToken(uuid, token);
	}
	
	/**
	 * Validates both that the user exists, but also that the token argument matches
	 * the user's token
	 * 
	 * @param token
	 * @param user
	 * @return Boolean - true=both the user and the token are valid, false=the user
	 *         exists, or null= that the user does not exist
	 */
	public Boolean isValidToken(final @NonNull String token, final @NonNull UUID uuid) {
		Optional<User> oUserLookup = this.findByUuid(uuid);
		if (oUserLookup.isEmpty() == true) {
			return null;
		}
		User u = oUserLookup.get();
		if (token.equals(u.getUuid().toString())) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;

	}

	/**
	 * Validates that at least one User's token matches the input argument token and
	 * that both user's are users exist. This prevents orphan records.
	 * 
	 * @param token - the String token. Most likely passed in through a header
	 * @param uuid1 - either a sender's or recipient's UUID
	 * @param uuid2 - either a sender or recipient's UUID
	 * @return Boolean -
	 */
	public Boolean isValidToken(final @NonNull String token, final @NonNull UUID uuid1, final @NonNull UUID uuid2) {
		return isValidToken(token, uuid1) || isValidToken(token, uuid2);
	}

	/**
	 * Tests for the existence and validity of the users and their tokens
	 * 
	 * @param token - a non-null token
	 * @param user1 - a non-null maybe non-persisted User
	 * @param user2 - a non-null maybe non-persisted User
	 * @return true, false or null. true - both users exist and at least one valid
	 *         token, false - both valid users and token is not valid (mean either
	 *         user must login), null - neither user is valid
	 */
	public Boolean isValidToken(final @NonNull String token, final @NonNull User user1, final @NonNull User user2) {
		Boolean b1 = isValidToken(token, user1.getUuid());
		Boolean b2 = isValidToken(token, user2.getUuid());
		if (b1 == null && b2 == null) {
			return null;
		}
		if (b1 == false && b2 == false) {
			return false;
		}
		return (b1 != null && b2 != null) && (b1 || b2);
	}

	public User logout(final @NonNull String token, final UUID uuid) throws BadRequestException {

		logger.debug("logout user.uuid= '" + uuid.toString() + "'");
		Optional<User> oUser = userRepository.findByToken(token);
		if (oUser.isEmpty()) {
			throw new BadRequestException("user matching token does not exist.");
		}
		User u = oUser.get();
		u.setToken(null);
		User uSaved = null;
		if ((uSaved = userRepository.save(u)) != null) {
			return uSaved;
		}
		return uSaved;
	}

	/**
	 * Ensures that at least the UUID is valid.
	 * 
	 * @param user
	 * @return the saved User
	 */
	public User save(final @NonNull User user) {
		if (user.getUuid() == null) {
			user.setUuid(UUID.randomUUID());
		}
		//return userRepository.save(user.getUuid(), user.getUsername(), user.getPassword(), user.getToken());
		return userRepository.save(user);
	}
	
	public User save(final @NonNull UUID uuid, final @NonNull String username, final @NonNull String password, final @Nullable String token) {
		return userRepository.save(uuid, username, password, token);
	}

}
