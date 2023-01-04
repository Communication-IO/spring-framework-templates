package com.ahlquist.controllers;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

	private static final Logger logger = LoggerFactory.getLogger(HealthCheckController.class);

	@RequestMapping(value = "/check", method = RequestMethod.GET, produces = { "application/json" })
	public @ResponseBody ResponseEntity<String> healthCheck() {
		logger.debug("[check] GET ******************************************");
		String msg = "{'health': 'ok', 'date': '" + new Date().toString() + "'}";
		logger.debug(msg);
		return new ResponseEntity<String>(msg, HttpStatus.OK);

	}
}
