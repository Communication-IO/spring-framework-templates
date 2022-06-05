package com.ahlquist.commio;

import java.util.HashMap;
import java.util.Map;

public class User {

	private String name;
	private String mail;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public User(String name, String mail) {
		super();
		this.name = name;
		this.mail = mail;
	}

	public static Map<Integer, User> userFactory() {
		User user1 = new User("Douglas", "douglas@gmail.com");
		User user2 = new User("Rein", "rein@gmail.com");
		User user3 = new User("Trudi", "trudi@gmail.com");
		User user4 = new User("Robert", "robert@gmail.com");

		Map<Integer, User> list = new HashMap<>();
		list.put(9898, user1);
		list.put(8456, user2);
		list.put(1234, user3);
		list.put(5678, user4);
		return list;

	}

}
