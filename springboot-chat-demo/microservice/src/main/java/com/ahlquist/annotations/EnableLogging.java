package com.ahlquist.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//import javax.enterprise.deploy.shared.ActionType;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.TYPE })
public @interface EnableLogging {
	// ActionType actionType();
	Class<? extends Enum<?>> enumClass();

	String[] disallowedValues();
}