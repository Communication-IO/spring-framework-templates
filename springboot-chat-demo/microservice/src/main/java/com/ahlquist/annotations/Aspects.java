package com.ahlquist.annotations;

import javax.servlet.http.HttpServletRequest;

import org.apache.coyote.Response;
//import org.apache.coyote.Response;
//import org.apache.catalina.connector.Response;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class Aspects {

	private static final Logger logger = LoggerFactory.getLogger(Aspects.class);

	@Before(value = "execution(* com.ahlquist.controllers.MediaController.*(..)) and args(name,empId)")
	public void beforeAdvice(JoinPoint joinPoint, String name, String empId) {
		logger.debug("Before method:" + joinPoint.getSignature());
		logger.debug("Creating Media with name - " + name + " and id - " + empId);
	}

	@After(value = "execution(* com.ahlquist.controllers.MediaController.*(..)) and args(name,empId)")
	public void afterAdvice(JoinPoint joinPoint, String name, String empId) {
		logger.debug("After method:" + joinPoint.getSignature());

		logger.debug("Successfully created Employee with name - " + name + " and id - " + empId);
	}

	@AfterReturning(pointcut = "execution(@com.ahlquist.controllers.MediaController.* * *(..)) && @annotation(enableLogging) && args(reqArg, reqArg1,..)", returning = "result")
	public void auditInfo(JoinPoint joinPoint, Object result, EnableLogging enableLogging, Object reqArg,
			String reqArg1) {

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();

		if (result instanceof Response) {
			Response responseObj = (Response) result;
		}

		String requestUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath() + request.getRequestURI() + "?" + request.getQueryString();

		String clientIp = request.getRemoteAddr();
		String clientRequest = reqArg.toString();
		// int httpResponseStatus = responseObj.getStatus();
		// responseObj. getEntity();
		// Can log whatever stuff from here in a single spot.
	}

	@AfterThrowing(pointcut = "execution(@com.ahlquist.controllers.MediaController.* * *(..)) && @annotation(enableLogging) && args(reqArg, reqArg1,..)", throwing = "exception")
	public void auditExceptionInfo(JoinPoint joinPoint, Throwable exception, EnableLogging enableLogging, Object reqArg,
			String reqArg1) {

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();

		String requestUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath() + request.getRequestURI() + "?" + request.getQueryString();

		exception.getMessage();
		exception.getCause();
		exception.printStackTrace();
		exception.getLocalizedMessage();
		// Can log whatever exceptions, requests, etc from here in a single spot.
	}
}