package com.tai.git.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class UserUsageLibrary {
	@Id
	@GeneratedValue
	private long id;

	@ManyToOne(targetEntity = GithubUser.class)
	private GithubUser githubUser;

	@ManyToOne(targetEntity = Library.class)
	private Library library;

	@Column(nullable = false)
	private long counter = 0;

	public UserUsageLibrary(GithubUser githubUser, Library library, long counter) {
		this.githubUser = githubUser;
		this.library = library;
		this.counter = counter;
	}

	public UserUsageLibrary() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public GithubUser getGithubUser() {
		return githubUser;
	}

	public void setGithubUser(GithubUser githubUser) {
		this.githubUser = githubUser;
	}

	public Library getLibrary() {
		return library;
	}

	public void setLibrary(Library library) {
		this.library = library;
	}

	public long getCounter() {
		return counter;
	}

	public void setCounter(long counter) {
		this.counter = counter;
	}
}
