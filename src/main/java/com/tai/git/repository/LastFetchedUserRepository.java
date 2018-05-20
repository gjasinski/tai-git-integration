package com.tai.git.repository;

import com.tai.git.model.LastFetchedUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LastFetchedUserRepository extends JpaRepository<LastFetchedUser, Long> {

}
