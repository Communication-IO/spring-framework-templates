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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ahlquist.exception.BadRequestException;
import com.ahlquist.exception.ResourceNotFoundException;
import com.ahlquist.models.User;
import com.ahlquist.request.FriendshipInviteRequest;
import com.ahlquist.request.UserLogoutRequest;
import com.ahlquist.request.UserRequest;
import com.ahlquist.response.Response;
import com.ahlquist.response.UserCreateResponse;
import com.ahlquist.services.UserService;

public class FriendsController {
	
	private static final Logger logger = LoggerFactory.getLogger(FriendsController.class);

	@Autowired
	@Qualifier(value = "UserService")
	private UserService userService;
	
	/**
	 * Logs out a previously successfully logged in User
	 * 
	 * @param friendshipInviteRequest
	 * @return
	 * @throws ResourceNotFoundException
	 */
	@RequestMapping(value = "/invite", 
			method = RequestMethod.POST, 
			consumes = { "application/json" }, 
			produces = { "application/json", "plain/text" })
	
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> invite(@NonNull @RequestBody FriendshipInviteRequest friendshipInviteRequest) 
			throws BadRequestException {
		logger.debug("[invite] POST ******************************************");
	
		if (!userService.findByEmail(friendshipInviteRequest.getInviteeEmail()).isEmpty()) {
			Response<FriendshipInviteRequest> r = new Response<>(false, "Unable to make friendship invitation from email.", friendshipInviteRequest);
			return new ResponseEntity<Response<FriendshipInviteRequest>>(r, HttpStatus.BAD_REQUEST);
		}
		
//		User user = new User();
//		user.setUuid(UUID.randomUUID());
//		user.setUsername(userRequest.getUsername());
//		user.setPassword(userRequest.getPassword());
//		User createdUser = userService.create(user);
//		
//		if (createdUser == null) {
//			Response<String> r = new Response<>(false, "Unable to create User.", null);
//			ResponseEntity<Response<String>> ro = new ResponseEntity<>(r, HttpStatus.BAD_REQUEST);
//			return ro;
//		}
//
//		UserCreateResponse ucr = new UserCreateResponse(createdUser.getUuid());
//		logger.debug("Successful user creation: " + userRequest.getUsername());
	
		return new ResponseEntity<>(null, HttpStatus.CREATED);

	}

}
