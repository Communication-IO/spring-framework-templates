package com.ahlquist.controllers;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ahlquist.exception.BadRequestException;
import com.ahlquist.exception.ResourceNotFoundException;
import com.ahlquist.models.User;
import com.ahlquist.request.UserLogoutRequest;
import com.ahlquist.request.UserRequest;
import com.ahlquist.response.Response;
import com.ahlquist.response.UserCreateResponse;
import com.ahlquist.response.UserResponse;
import com.ahlquist.services.UserService;
import com.ahlquist.utils.SimpleToken;

@Controller
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	@Qualifier(value = "UserService")
	private UserService userService;

	/**
	 * Creates a new 'User' from json with username and password. Username MUST be
	 * unique.
	 * 
	 * @param userRequest
	 * @return several values may be returned either a UserCreateResponse with the
	 *         uuid or an error
	 * @throws BadRequestException - when a duplicate user with similar username is
	 *                             attempted to be created
	 */
	@RequestMapping(value = "/user", method = RequestMethod.POST, consumes = { "application/json" }, produces = {
			"application/json" })
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@RequestBody UserRequest userRequest) 
			throws BadRequestException {
		logger.debug("[user] POST ******************************************");
		if (userRequest == null) {
			Response<UserRequest> r = new Response<>(false, "Could not create User", userRequest);
			ResponseEntity<Response<UserRequest>> ro = new ResponseEntity<>(r, HttpStatus.BAD_REQUEST);
			return ro;
		}

		if (!userService.exists(userRequest.getUsername()).isEmpty()) {
			Response<UserRequest> r = new Response<>(false, "Attempting to create duplicate user.", userRequest);
			ResponseEntity<Response<UserRequest>> ro = new ResponseEntity<>(r, HttpStatus.BAD_REQUEST);
			return ro;
		}
		User user = new User();
		user.setUuid(UUID.randomUUID());
		user.setUsername(userRequest.getUsername());
		user.setPassword(userRequest.getPassword());
		User createdUser = userService.create(user);
		
		if (createdUser == null) {
			Response<String> r = new Response<>(false, "Unable to create User.", null);
			ResponseEntity<Response<String>> ro = new ResponseEntity<>(r, HttpStatus.BAD_REQUEST);
			return ro;
		}

		UserCreateResponse ucr = new UserCreateResponse(createdUser.getUuid());
		logger.debug("Successful user creation: " + userRequest.getUsername());
	
		ResponseEntity<UserCreateResponse> ro = new ResponseEntity<>(ucr, HttpStatus.CREATED);
		return ro;
	}

	/**
	 * logs in a persisted user. TODO(dahlquist) ; there is no requirement for
	 * logout or expired token... but logout has been added.
	 * 
	 * @param userRequest containing a username and password
	 * @return UserResponse, contain the uuid and new token
	 * @throws ResourceNotFoundException when the user is not previously persisted
	 *                                   in the database
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = { "application/json", "application/json;charset=UTF-8" }, produces = {
			"application/json", "application/json;charset=UTF-8", "plain/text" })
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> login(@RequestBody UserRequest userRequest) {
		logger.debug("[login] POST ******************************************");
		Optional<User> oUser = userService.findByUsername(userRequest.getUsername());
		if (oUser.isPresent()) {
			// create token add user at oUser and save, then return the
			String token = SimpleToken.encode(userRequest.getUsername() + userRequest.getPassword());
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>> token: " + token + " length: " + token.length());
			User u = oUser.get();
			u.setToken(token);
			userService.save(u);
			UserResponse ur = new UserResponse(u.getUuid(), token);

			logger.debug("Successful user login: " + u.getUsername());
			return ResponseEntity.ok().body(ur);
		} else {
			String msg = "Could not create User with username and password unavailable ";
			logger.error(msg);
			
			ResponseEntity<String> ro = new ResponseEntity<>(msg,
					HttpStatus.NOT_ACCEPTABLE);
			return ro;
		}
	}

	/**
	 * Logs out a previously successfully logged in User
	 * 
	 * @param userRequest
	 * @return
	 * @throws ResourceNotFoundException
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.POST, consumes = { "application/json" }, produces = {
			"application/json", "plain/text" })
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> logout(@RequestHeader Map<String, String> headers, @NonNull @RequestBody UserLogoutRequest userLogoutRequest) 
			throws ResourceNotFoundException, BadRequestException {
		
		logger.debug("[logout] POST ******************************************");
		final UUID uuid = UUID.fromString(userLogoutRequest.getUuid());
		final String token = headers.get("token");
		
		logger.debug("userLogoutRequest: " + userLogoutRequest.toString());

		Optional<User> oUser = userService.findByToken(token);
		if (oUser.isEmpty()) {
			String msg = "user matching token " + uuid.toString() + " does NOT exist";
			logger.error(msg);

			ResponseEntity<String> ro = new ResponseEntity<>(msg,
					HttpStatus.NOT_FOUND);
			return ro;
		}

		// Only userLogoutRequest with match the token with persisted token can logout
		// this is a security feature. This does NOT allow requests to logout users when
		// you don't know their tokens
		User u = userService.logout(token, uuid);
		if(u.getToken()!=null) {
			logger.error("user token not reset to null: " + u.toString());
		}
		if(u != null && u.getToken() == null) {
			ResponseEntity<User> ro = 
					new ResponseEntity<User>(u, HttpStatus.OK);
			return ro;
		}
		ResponseEntity<String> ro = new ResponseEntity<>("Unsuccessful logout",
				HttpStatus.BAD_REQUEST);
		return ro;
	}
}
