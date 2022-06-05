package com.ahlquist.commio.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ahlquist.commio.model.User;

@Service
public class UserService {
	private List<User> topicList = new ArrayList<>(Arrays.asList(

			new User("1", "Hoover", "HooverDude@gmail.com"),
			new User("2", "Douglas", "Peanut@gmail.com"), new User("3", "Java", "java@gmail.com")

	));

	public List<User> getTopicList() {
		return topicList;
	}

	public void setTopicList(List<User> topicList) {
		this.topicList = topicList;
	}
	
	
}
