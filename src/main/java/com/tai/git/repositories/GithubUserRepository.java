package com.tai.git.repositories;

import com.tai.git.models.GithubUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface GithubUserRepository extends PagingAndSortingRepository<GithubUser, Long> {
	Optional<GithubUser> getByGithubId(long githubId);

	Optional<GithubUser> getByGithubLogin(String login);

	Long countByProcessed(boolean processed);
}
