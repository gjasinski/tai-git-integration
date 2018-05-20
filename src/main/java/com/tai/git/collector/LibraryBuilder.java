package com.tai.git.collector;

import com.tai.git.model.Library;
import com.tai.git.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

class LibraryBuilder {
	private final LibraryRepository libraryRepository;

	LibraryBuilder(LibraryRepository libraryRepository) {
		this.libraryRepository = libraryRepository;
	}

	Optional<Library> addToDatabase(String dependecy) {
		Library library = null;
		if (validateDependency(dependecy)) {
			String groupId = extractGroupId(dependecy);
			String artifactId = extractArtifactId(dependecy);
			String version = extractVersion(dependecy);
			library = libraryRepository.findByGroupIdEqualsAndArtifactIdEqualsAndVersionEquals(groupId, artifactId, version);
			if (library != null) {
				library.incrementCounter();
			} else {
				library = new Library(groupId, artifactId, version);
			}
			libraryRepository.save(library);
		}
		return Optional.ofNullable(library);
	}

	private String extractVersion(String dependecy) {
		return dependecy.substring(dependecy.indexOf("<version>") + 9, dependecy.indexOf("</version>"));
	}

	private String extractArtifactId(String dependecy) {
		return dependecy.substring(dependecy.indexOf("<artifactId>") + 12, dependecy.indexOf("</artifactId>"));
	}

	private String extractGroupId(String dependecy) {
		return dependecy.substring(dependecy.indexOf("<groupId>") + 9, dependecy.indexOf("</groupId>"));
	}

	private boolean validateDependency(String dependecy) {
		return dependecy.contains("<version>") && dependecy.contains("<artifactId>") && dependecy.contains("<groupId>");
	}
}
