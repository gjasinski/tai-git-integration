package com.tai.git.collector;


import com.tai.git.collector.dtos.GitUserDTO;
import com.tai.git.collector.dtos.QueryResultDTO;
import com.tai.git.models.GithubUser;
import com.tai.git.models.GithubUserLibraryUsage;
import com.tai.git.models.Library;
import com.tai.git.repositories.GithubUserLibraryUsageRepository;
import com.tai.git.repositories.GithubUserRepository;
import com.tai.git.repositories.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LibraryDataCollector implements Runnable {
	private static final int _6_SECONDS = 6000;
	private final LibraryBuilder libraryBuilder;
	private final PomCleaner pomCleaner = new PomCleaner();
	private final PomSearcher pomDownloader = new PomSearcher();
	private final GithubUserRepository githubUserRepository;
	private final GithubUserLibraryUsageRepository githubUserUsageLibraryRepository;
	private final UserProviderService userProviderService;

	@Autowired
	public LibraryDataCollector(GithubUserRepository githubUserRepository, GithubUserLibraryUsageRepository githubUserUsageLibraryRepository, LibraryRepository libraryRepository, UserProviderService userProviderService) {
		this.githubUserRepository = githubUserRepository;
		this.githubUserUsageLibraryRepository = githubUserUsageLibraryRepository;
		this.libraryBuilder = new LibraryBuilder(libraryRepository);
		this.userProviderService = userProviderService;
	}

	@Override
	public void run() {
		for (int i = 0; i < 0/*userProviderService.getTotalCount()*/; i++) {
			try {
				Thread.sleep(_6_SECONDS);
				GitUserDTO user = userProviderService.getNextUser();
				fetchLibraries(user);
			} catch (Exception e) {
				//todo move from stdout to logs
				e.printStackTrace();
			}
		}
	}

	public List<Library> fetchLibraries(GitUserDTO user) {
		GithubUser githubUser = saveUser(user);
		List<Library> libraries = Collections.emptyList();
		try {
			QueryResultDTO[] items = pomDownloader.downloadPOMForUser(user.getLogin());
			libraries = pomCleaner.cleanPom(Arrays.stream(items))
					.map(libraryBuilder::addToDatabase)
					.filter(Optional::isPresent)
					.map(Optional::get)
					.peek(library -> saveUserLibraryUsage(githubUser, library))
					.collect(Collectors.toList());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			githubUser.setProcessed(true);
			githubUserRepository.save(githubUser);
		}
		return libraries;
	}

	private GithubUser saveUser(GitUserDTO userDTO) {
		return githubUserRepository.getByGithubId(userDTO.getId())
				.orElseGet(() -> createGithubUserAndSave(userDTO));
	}

	private GithubUser createGithubUserAndSave(GitUserDTO userDTO) {
		GithubUser githubUser = new GithubUser(userDTO.getId(), userDTO.getLogin(), false);
		githubUserRepository.save(githubUser);
		return githubUser;
	}

	private GithubUserLibraryUsage saveUserLibraryUsage(GithubUser githubUser, Library library) {
		GithubUserLibraryUsage githubUserUsageLibrary = githubUserUsageLibraryRepository.getByGithubUserEqualsAndLibraryEquals(githubUser, library)
				.orElseGet(() -> createGithubUserLibraryUsage(githubUser, library));
		githubUserUsageLibrary.incrementCounter();
		githubUserUsageLibraryRepository.save(githubUserUsageLibrary);
		return githubUserUsageLibrary;
	}

	private GithubUserLibraryUsage createGithubUserLibraryUsage(GithubUser githubUser, Library library) {
		GithubUserLibraryUsage githubUserUsageLibrary = new GithubUserLibraryUsage(githubUser, library, 0);
		githubUserUsageLibraryRepository.save(githubUserUsageLibrary);
		return githubUserUsageLibrary;
	}

}
