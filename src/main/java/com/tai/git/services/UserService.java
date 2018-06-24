package com.tai.git.services;

import com.tai.git.collector.LibraryDataCollector;
import com.tai.git.collector.UserProviderService;
import com.tai.git.collector.dtos.GitUserDTO;
import com.tai.git.dtos.LibraryDTO;
import com.tai.git.dtos.UserDTO;
import com.tai.git.models.GithubUser;
import com.tai.git.models.GithubUserLibraryUsage;
import com.tai.git.repositories.GithubUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
	private final GithubUserRepository githubUserRepository;
	private final LibraryDataCollector libraryDataCollector;
	private final UserProviderService userProviderService;

	@Autowired
	public UserService(GithubUserRepository githubUserRepository, LibraryDataCollector libraryDataCollector, UserProviderService userProviderService) {
		this.githubUserRepository = githubUserRepository;
		this.libraryDataCollector = libraryDataCollector;
		this.userProviderService = userProviderService;
	}

	public List<UserDTO> getAllUsers() {
		return this.githubUserRepository.findAll()
				.stream()
				.map(user -> new UserDTO(user.getId(), user.getGithubId(), user.getGithubLogin(), user.isProcessed()))
				.collect(Collectors.toList());
	}

	//todo przetestowac
	public List<LibraryDTO> getAllUserLibraries(String login) {
		Optional<GithubUser> user = githubUserRepository.getByGithubLogin(login);
		if (user.isPresent() && user.get().isProcessed()) {
			return user.get().getUserUsageLibraries()
					.stream()
					.map(this::mapToLibraryDTO)
					.collect(Collectors.toList());
		} else {
			GitUserDTO userByLogin = userProviderService.getUserByLogin(login);
			libraryDataCollector.fetchLibraries(userByLogin);
			return githubUserRepository.getByGithubLogin(login)
					.get()
					.getUserUsageLibraries()
					.stream()
					.map(this::mapToLibraryDTO)
					.collect(Collectors.toList());
		}
	}

	private LibraryDTO mapToLibraryDTO(GithubUserLibraryUsage usage) {
		return new LibraryDTO(usage.getLibrary());
	}
}
