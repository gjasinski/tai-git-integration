package com.tai.git.controllers;

import com.tai.git.dtos.LibraryDTO;
import com.tai.git.services.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "https://obscure-atoll-92583.herokuapp.com", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class LibraryController {

	private final LibraryService libraryService;

	@Autowired
	public LibraryController(LibraryService libraryService) {
		this.libraryService = libraryService;
	}

	@GetMapping("/libraries")
	public HttpEntity<List<LibraryDTO>> getLibraries(@RequestParam int limit, @RequestParam int page, @RequestParam boolean order){
		if (order) {
			return new HttpEntity<>(libraryService.getLibrariesTopUsage(limit, page));
		}
		else{
			return new HttpEntity<>(libraryService.getLibraries(limit, page));
		}
	}
}
