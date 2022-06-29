package com.ahlquist.commio.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class UserAfterAspect {

	@After("args(name)")
	public void logStringArguments(String name) {
		System.out.println("Running After Advice. String argument passed=" + name);
	}

	@AfterThrowing("within(com.ahlquist.commio.model.User)")
	public void logExceptions(JoinPoint joinPoint) {
		System.out.println("Exception thrown in User Method=" + joinPoint.toString());
	}

	@AfterReturning(pointcut = "execution(* getFirstName())", returning = "returnString")
	public void getNameReturningAdvice(String returnString) {
		System.out.println("getNameReturningAdvice executed. Returned String=" + returnString);
	}

}