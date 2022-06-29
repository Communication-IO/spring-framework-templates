package com.ahlquist.commio.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class UserAroundAspect {

	@Around("execution(* com.ahlquist.commio.model.User.getFirstName())")
	public Object userAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
		System.out.println("Before invoking getFirstName() method");
		Object value = null;
		
		try {
			value = proceedingJoinPoint.proceed();
		} 
		catch (Throwable e) {
			e.printStackTrace();
		}
		
		System.out.println("After invoking getFirstName() method. Return value=" + value);
		return value;
	}
}