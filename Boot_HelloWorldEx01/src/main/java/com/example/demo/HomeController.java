package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	@RequestMapping(value = "/")
	public String root() {
		return "Hello Spring Boot!";
	}

	@RequestMapping(value = "/kor")
	public String rootKor() {
		return "반갑다 스프링 부트야!";
	}
}
