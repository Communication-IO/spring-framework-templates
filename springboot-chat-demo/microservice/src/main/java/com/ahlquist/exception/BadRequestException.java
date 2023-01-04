package com.ahlquist.exception;

@SuppressWarnings("serial")
public class BadRequestException extends Exception {
	public BadRequestException() {
		super();
	}

	public BadRequestException(String msg) {
		super(msg);
	}

}
