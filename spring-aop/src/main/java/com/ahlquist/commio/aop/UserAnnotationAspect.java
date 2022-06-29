package com.ahlquist.commio.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class UserAnnotationAspect {

	@Before("@annotation(com.ahlquist.commio.aop.Loggable)")
	public void myAdvice() {
		System.out.println("Executing myAdvice!!");
	}
}