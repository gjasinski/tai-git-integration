package com.tai.git.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Library {
	@Id
	@GeneratedValue
	private long id;

	@Column(nullable = false)
	private String groupId;

	@Column(nullable = false)
	private String artifactId;

	@Column(nullable = false)
	private String version;

	@Column(nullable = false)
	private long counter = 0;

	@Column(nullable = false)
	@OneToMany(mappedBy = "library", targetEntity = GithubUserLibraryUsage.class)
	private List<GithubUserLibraryUsage> userUsageLibraries = new LinkedList<>();

	private Library(String groupId, String artifactId, String version, long counter) {
		this.groupId = groupId;
		this.artifactId = artifactId;
		this.version = version;
		this.counter = counter;
	}

	public Library(String groupId, String artifactId, String version) {
		this(groupId, artifactId, version, 1);
	}

	public Library() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public List<GithubUserLibraryUsage> getUserUsageLibraries() {
		return userUsageLibraries;
	}

	public void setUserUsageLibraries(List<GithubUserLibraryUsage> userUsageLibraries) {
		this.userUsageLibraries = userUsageLibraries;
	}

	public long getCounter() {
		return counter;
	}

	public void setCounter(long counter) {
		this.counter = counter;
	}

	public void incrementCounter(){
		this.counter++;
	}
}
