package com.ahlquist.exception;

@SuppressWarnings("serial")
public class HealthCheckException extends Exception {

	public HealthCheckException() {
		super();
	}

	public HealthCheckException(String msg) {
		super(msg);
	}

}
