package com.ahlquist.commio.app;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ahlquist.commio.service.UserService;

public class SpringAopTemplate {

	public static void main(String... args) {

		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-config.xml");
		UserService userService = ctx.getBean("userService", UserService.class);

		System.out.println(userService.getUser().getFirstName());
		System.out.println(userService.getUser().getLastName());

		userService.getUser().setFirstName("Douglas");

		userService.getUser().throwException();

		ctx.close();
	}
}