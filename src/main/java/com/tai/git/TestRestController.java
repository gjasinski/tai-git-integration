package com.tai.git;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestRestController {


	@GetMapping("/greetings/{name}")
	public String test(@PathVariable(name = "name") String name) {
		return "Hello " + name;
	}
}
