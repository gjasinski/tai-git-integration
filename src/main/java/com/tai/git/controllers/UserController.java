package com.tai.git.controllers;

import com.tai.git.dtos.LibraryDTO;
import com.tai.git.dtos.UserDTO;
import com.tai.git.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
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

	@GetMapping("/users/libraries")
	public HttpEntity<List<LibraryDTO>> getAllUserLibraries(@RequestParam String login){
		return new HttpEntity<>(userService.getAllUserLibraries(login));
	}

	@GetMapping("/users/{login}/followers")
	public HttpEntity<List<String>> getAllUserFollowers(@PathVariable String login){
		return new HttpEntity<>(userService.getAllUserFollowers(login));
	}
}
