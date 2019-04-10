package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

	@GetMapping(value = "public")
	public String publico() {
		return "Public Page";
	}

	@GetMapping("/private")
	public String privates() {
		return "Private Page";
	}

	@GetMapping("/admin")
	public String admin() {
		return "Administrator Page";
	}

}
