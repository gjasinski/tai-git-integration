package com.tai.git.repositories;

import com.tai.git.models.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface LibraryRepository extends PagingAndSortingRepository<Library, Long> {
	Library findByGroupIdEqualsAndArtifactIdEqualsAndVersionEquals(String groupId, String artifactId, String version);

	List<Library> findAll();

//	List<Library> findAllOrOrderByCounter();
}
