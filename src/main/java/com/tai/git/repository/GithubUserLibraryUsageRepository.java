package com.tai.git.repository;

import com.tai.git.model.GithubUser;
import com.tai.git.model.GithubUserLibraryUsage;
import com.tai.git.model.Library;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GithubUserLibraryUsageRepository extends JpaRepository<GithubUserLibraryUsage, Long> {
	Optional<GithubUserLibraryUsage> getByGithubUserEqualsAndLibraryEquals(GithubUser githubUser, Library library);
}
