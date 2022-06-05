package com.ahlquist.commio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public ResponseEntity<List<UserVo>> listAllUsers() {
		List<UserVo> list = new ArrayList<>();
		UserVo obj = new UserVo();
		obj.setName("User1");
		obj.setEmail("hooverdud@gmail.com");
		list.add(obj);
		return new ResponseEntity<List<UserVo>>(list, HttpStatus.OK);
	}
}
