package com.ahlquist.commio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ahlquist.commio.entity.User;
import com.ahlquist.commio.repository.UserRepository;

@RestController
public class HomeController {

	@Autowired
	UserRepository repository;

	@GetMapping({ "/", "/getcall" })
	public ResponseEntity<List<User>> listAllUsers() {
		List<User> userList = new ArrayList<>();
		userList = (List<User>) repository.findAll();
		List<User> vo = new ArrayList<>();
		for (User users : userList) {
			User obj = new User();
			obj.setId(users.getId());
			obj.setName(users.getName());
			vo.add(obj);
		}
		return new ResponseEntity<List<User>>(vo, HttpStatus.OK);
	}
}
