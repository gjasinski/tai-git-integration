package com.tai.git.services;

import com.tai.git.dtos.UserDTO;
import com.tai.git.repositories.GithubUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
	private final GithubUserRepository githubUserRepository;

	@Autowired
	public UserService(GithubUserRepository githubUserRepository) {
		this.githubUserRepository = githubUserRepository;
	}

	public List<UserDTO> getAllUsers() {
		return this.githubUserRepository.findAll()
				.stream()
				.map(user -> new UserDTO(user.getId(), user.getGithubId(), user.getGithubLogin(), user.isProcessed()))
				.collect(Collectors.toList());
	}
}
