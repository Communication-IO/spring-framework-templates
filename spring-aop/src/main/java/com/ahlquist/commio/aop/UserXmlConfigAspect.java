package com.ahlquist.commio.aop;

import org.aspectj.lang.ProceedingJoinPoint;

public class UserXmlConfigAspect {

	public Object userAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
		System.out.println("UserXMLConfigAspect:: Before invoking getFristName() method");
		Object value = null;
		
		try {
			value = proceedingJoinPoint.proceed();
		} 
		catch (Throwable e) {
			e.printStackTrace();
		}
		
		System.out.println("UserXMLConfigAspect:: After invoking getName() method. Return value=" + value);
		return value;
	}
}