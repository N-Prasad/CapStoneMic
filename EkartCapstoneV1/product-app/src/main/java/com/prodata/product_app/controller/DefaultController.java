package com.prodata.product_app.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
//@RefreshScope
//@RequestMapping("/defaultController")
public class DefaultController {

	@Value("${propertyfilename}")
	private String propertyFileName;

	@Value("${defaultmessage}")
	private String defaultMessage;

	@GetMapping("/getPropertyFileName")
	public String getPropertyFileName() {
		return propertyFileName;
	}

	@GetMapping("/getDefaultMessage")
	public String getDefaultMessage() {
		return defaultMessage;
	}

}
