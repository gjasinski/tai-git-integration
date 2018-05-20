package com.tai.git.repositories;

import com.tai.git.models.GithubUser;
import com.tai.git.models.GithubUserLibraryUsage;
import com.tai.git.models.Library;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GithubUserLibraryUsageRepository extends JpaRepository<GithubUserLibraryUsage, Long> {
	Optional<GithubUserLibraryUsage> getByGithubUserEqualsAndLibraryEquals(GithubUser githubUser, Library library);
}
