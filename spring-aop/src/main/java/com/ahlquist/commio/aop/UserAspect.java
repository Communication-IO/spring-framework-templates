package com.ahlquist.commio.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class UserAspect {

	@Before("execution(public String getFirstName())")
	public void getFirstNameAdvice() {
		System.out.println("Executing Advice on getFirstName()");
	}

	@Before("execution(* com.ahlquist.commio.aop.service.*.get*())")
	public void getAllAdvice() {
		System.out.println("Service method getter called");
	}
}