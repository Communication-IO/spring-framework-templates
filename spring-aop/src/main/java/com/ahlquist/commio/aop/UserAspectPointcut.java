package com.ahlquist.commio.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class UserAspectPointcut {

	@Before("getNamePointcut()")
	//@Pointcut("getNamePointcut()")
	public void loggingAdvice() {
		System.out.println("Executing loggingAdvice on getName()");
	}

	@Before("getNamePointcut()")
	public void secondAdvice() {
		System.out.println("Executing secondAdvice on getName()");
	}

	@Pointcut("execution(public String getName())")
	public void getNamePointcut() {
	}

	@Before("allMethodsPointcut()")
	//@Pointcut("allMethodsPointcut()")
	public void allServiceMethodsAdvice() {
		System.out.println("Before executing service method");
	}

	/**
	 * Pointcut to execute on all the methods of classes in a package
	 */
	@Pointcut("within(com.ahlquist.commio.service.*)")
	public void allMethodsPointcutWithin() {
	}

}
