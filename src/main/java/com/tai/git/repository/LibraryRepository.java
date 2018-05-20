package com.tai.git.repository;

import com.tai.git.model.Library;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryRepository extends JpaRepository<Library, Long> {
	Library findByGroupIdEqualsAndArtifactIdEqualsAndVersionEquals(String groupId, String artifactId, String version);
}
