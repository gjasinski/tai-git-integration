package com.tai.git.services;

import com.tai.git.dtos.LibraryDTO;
import com.tai.git.repositories.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibraryService {
	private final LibraryRepository libraryRepository;

	@Autowired
	public LibraryService(LibraryRepository libraryRepository) {
		this.libraryRepository = libraryRepository;
	}

	public List<LibraryDTO> getLibrariesTopUsage(int limit, int page) {
		PageRequest pr = new PageRequest(page, limit, Sort.Direction.DESC, "counter");
		return libraryRepository.findAll(pr)
				.getContent()
				.stream()
				.map(LibraryDTO::new)
				.collect(Collectors.toList());
	}

	public List<LibraryDTO> getLibraries(int limit, int page) {
		PageRequest pr = new PageRequest(page, limit);
		return libraryRepository.findAll(pr).getContent()
				.stream()
				.map(LibraryDTO::new)
				.collect(Collectors.toList());
	}
}
