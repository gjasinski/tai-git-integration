package com.tai.git.dtos;

public class UserDTO {
	private long id;
	private long githubId;
	private String githubLogin;
	private boolean processed;

	public UserDTO(long id, long githubId, String githubLogin, boolean processed) {
		this.id = id;
		this.githubId = githubId;
		this.githubLogin = githubLogin;
		this.processed = processed;
	}

	public UserDTO() {
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
}
