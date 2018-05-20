package com.tai.git.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.LinkedList;
import java.util.List;

@Entity
public class GithubUser {
	@Id
	@GeneratedValue
	private long id;

	@Column(nullable = false)
	private long githubId;

	@Column(nullable = false)
	private String githubLogin;

	@Column(nullable = false)
	private boolean processed;

	@Column(nullable = false)
	@OneToMany(mappedBy = "githubUser", targetEntity = UserUsageLibrary.class)
	private List<UserUsageLibrary> userUsageLibraries = new LinkedList<>();

	public GithubUser(long githubId, String githubLogin, boolean processed) {
		this.githubId = githubId;
		this.githubLogin = githubLogin;
		this.processed = processed;
	}

	public GithubUser() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getGithubId() {
		return githubId;
	}

	public void setGithubId(long githubId) {
		this.githubId = githubId;
	}

	public String getGithubLogin() {
		return githubLogin;
	}

	public void setGithubLogin(String githubLogin) {
		this.githubLogin = githubLogin;
	}

	public boolean isProcessed() {
		return processed;
	}

	public void setProcessed(boolean processed) {
		this.processed = processed;
	}

	public List<UserUsageLibrary> getUserUsageLibraries() {
		return userUsageLibraries;
	}

	public void setUserUsageLibraries(List<UserUsageLibrary> userUsageLibraries) {
		this.userUsageLibraries = userUsageLibraries;
	}
}
