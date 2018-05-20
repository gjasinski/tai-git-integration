package com.tai.git.model;

import javax.persistence.Column;
import javax.persistence.Id;

public class LastFetchedUser {
	private final static String LAST_FETCHED_USER = "LAST_FETCHED_USER";

	@Id
	private String lastFetchedUser = LAST_FETCHED_USER;

	@Column(nullable = false)
	private long lastFetchedUserId;

	public LastFetchedUser(String lastFetchedUser, long lastFetchedUserId) {
		this.lastFetchedUser = lastFetchedUser;
		this.lastFetchedUserId = lastFetchedUserId;
	}

	public LastFetchedUser() {
	}

	public static String getLastFetchedUser() {
		return LAST_FETCHED_USER;
	}

	public long getLastFetchedUserId() {
		return lastFetchedUserId;
	}

	public void setLastFetchedUserId(long lastFetchedUserId) {
		this.lastFetchedUserId = lastFetchedUserId;
	}
}
