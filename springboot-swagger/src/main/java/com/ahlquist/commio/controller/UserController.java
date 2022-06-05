package com.ahlquist.commio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ahlquist.commio.model.User;
import com.ahlquist.commio.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api
public class UserController {

	@Autowired
	UserService service;

	@GetMapping("/user")
	@ApiOperation(value = "Find all User", notes = "Retrieving the collection of User", response = User[].class)
	@ApiResponses({ @ApiResponse(code = 200, message = "Success", response = User[].class) })

	public List<User> findAll() {
		return service.getTopicList();
	}

}