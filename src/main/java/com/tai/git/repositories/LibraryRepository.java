package com.tai.git.repositories;

import com.tai.git.models.Library;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryRepository extends JpaRepository<Library, Long> {
	Library findByGroupIdEqualsAndArtifactIdEqualsAndVersionEquals(String groupId, String artifactId, String version);
}
