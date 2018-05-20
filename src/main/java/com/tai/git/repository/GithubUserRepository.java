package com.tai.git.repository;

import com.tai.git.model.GithubUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GithubUserRepository extends JpaRepository<GithubUser, Long> {
	Optional<GithubUser> getByGithubId(long githubId);
}
