package com.tai.git.controllers;

import com.tai.git.dtos.UserDTO;
import com.tai.git.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/users")
	public HttpEntity<List<UserDTO>> getAllUsers(){
		return new HttpEntity<>(userService.getAllUsers());
	}
}
