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

@CrossOrigin(origins = "https://obscure-atoll-92583.herokuapp.com")
@RestController
@RequestMapping("/api")
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/users")
	public HttpEntity<List<UserDTO>> getAllUsers(@RequestParam int page, @RequestParam int limit){
		return new HttpEntity<>(userService.getAllUsers(page, limit));
	}

	@GetMapping("/users/libraries")
	public HttpEntity<List<LibraryDTO>> getAllUserLibraries(@RequestParam String login){
		return new HttpEntity<>(userService.getAllUserLibraries(login));
	}

	@GetMapping("/users/following")
	public HttpEntity<List<String>> getAllUserFollowing(@RequestParam String login){
		return new HttpEntity<>(userService.getAllUserFollowing(login));
	}
}
