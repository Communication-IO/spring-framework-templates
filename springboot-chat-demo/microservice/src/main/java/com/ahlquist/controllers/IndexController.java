package com.ahlquist.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @author douglasahlquist
 */
@Controller
public class IndexController implements ErrorController {

	/**
	 * 
	 * @return String - the error message
	 */
	@RequestMapping("/error")
	@ResponseBody
	public String getErrorPath() {
		return "No Mapping Found";
	}
}