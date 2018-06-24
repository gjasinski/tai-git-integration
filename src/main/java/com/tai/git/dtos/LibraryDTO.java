package com.tai.git.dtos;

import com.tai.git.models.Library;

public class LibraryDTO {
	private Long id;
	private String groupId;
	private String artifactId;
	private String version;
	private long counter;

	public LibraryDTO(long id, String groupId, String artifactId, String version, long counter) {
		this.id = id;
		this.groupId = groupId;
		this.artifactId = artifactId;
		this.version = version;
		this.counter = counter;
	}

	public LibraryDTO(Library library){
		this.id = library.getId();
		this.groupId = library.getGroupId();
		this.artifactId = library.getArtifactId();
		this.version = library.getVersion();
		this.counter = library.getCounter();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getArtifactId() {
		return artifactId;
	}

	public void setArtifactId(String artifactId) {
		this.artifactId = artifactId;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public long getCounter() {
		return counter;
	}

	public void setCounter(long counter) {
		this.counter = counter;
	}

	@Override
	public String toString() {
		return "LibraryDTO{" +
				"id=" + id +
				", groupId='" + groupId + '\'' +
				", artifactId='" + artifactId + '\'' +
				", version='" + version + '\'' +
				", counter=" + counter +
				'}';
	}
}
