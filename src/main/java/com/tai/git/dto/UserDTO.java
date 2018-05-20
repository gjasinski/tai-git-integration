package com.tai.git.dto;

public class UserDTO {
	private String login;
	private long id;
	private String avatar_url;
	private String gravatarId;
	private String url;
	private String htmlUrl;
	private String followersUrl;
	private String followingUrl;
	private String gistsUrl;
	private String starredUrl;
	private String subscriptionsUrl;
	private String organizationsUrl;
//	private String reposUrl;
//	private String eventsUrl;
//	private String receivedEventsUrl;
//	private String type;
//	private String siteAdmin;

	public UserDTO(String login, long id, String avatar_url, String gravatarId, String url, String htmlUrl, String followersUrl, String followingUrl, String gistsUrl, String starredUrl, String subscriptionsUrl, String organizationsUrl, String reposUrl, String eventsUrl, String receivedEventsUrl, String type, String siteAdmin) {
		this.login = login;
		this.id = id;
		this.avatar_url = avatar_url;
		this.gravatarId = gravatarId;
		this.url = url;
		this.htmlUrl = htmlUrl;
		this.followersUrl = followersUrl;
		this.followingUrl = followingUrl;
		this.gistsUrl = gistsUrl;
		this.starredUrl = starredUrl;
		this.subscriptionsUrl = subscriptionsUrl;
		this.organizationsUrl = organizationsUrl;
//		this.reposUrl = reposUrl;
//		this.eventsUrl = eventsUrl;
//		this.receivedEventsUrl = receivedEventsUrl;
//		this.type = type;
//		this.siteAdmin = siteAdmin;
	}

	public UserDTO(String login, long id, String avatar_url, String gravatarId, String url, String htmlUrl, String followersUrl, String followingUrl, String gistsUrl, String starredUrl, String subscriptionsUrl, String organizationsUrl) {
		this.login = login;
		this.id = id;
		this.avatar_url = avatar_url;
		this.gravatarId = gravatarId;
		this.url = url;
		this.htmlUrl = htmlUrl;
		this.followersUrl = followersUrl;
		this.followingUrl = followingUrl;
		this.gistsUrl = gistsUrl;
		this.starredUrl = starredUrl;
		this.subscriptionsUrl = subscriptionsUrl;
		this.organizationsUrl = organizationsUrl;
	}

	public UserDTO() {
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAvatar_url() {
		return avatar_url;
	}

	public void setAvatar_url(String avatar_url) {
		this.avatar_url = avatar_url;
	}

	public String getGravatarId() {
		return gravatarId;
	}

	public void setGravatarId(String gravatarId) {
		this.gravatarId = gravatarId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getHtmlUrl() {
		return htmlUrl;
	}

	public void setHtmlUrl(String htmlUrl) {
		this.htmlUrl = htmlUrl;
	}

	public String getFollowersUrl() {
		return followersUrl;
	}

	public void setFollowersUrl(String followersUrl) {
		this.followersUrl = followersUrl;
	}

	public String getFollowingUrl() {
		return followingUrl;
	}

	public void setFollowingUrl(String followingUrl) {
		this.followingUrl = followingUrl;
	}

	public String getGistsUrl() {
		return gistsUrl;
	}

	public void setGistsUrl(String gistsUrl) {
		this.gistsUrl = gistsUrl;
	}

	public String getStarredUrl() {
		return starredUrl;
	}

	public void setStarredUrl(String starredUrl) {
		this.starredUrl = starredUrl;
	}

	public String getSubscriptionsUrl() {
		return subscriptionsUrl;
	}

	public void setSubscriptionsUrl(String subscriptionsUrl) {
		this.subscriptionsUrl = subscriptionsUrl;
	}

	public String getOrganizationsUrl() {
		return organizationsUrl;
	}

	public void setOrganizationsUrl(String organizationsUrl) {
		this.organizationsUrl = organizationsUrl;
	}
//
//	public String getReposUrl() {
//		return reposUrl;
//	}
//
//	public void setReposUrl(String reposUrl) {
//		this.reposUrl = reposUrl;
//	}
//
//	public String getEventsUrl() {
//		return eventsUrl;
//	}
//
//	public void setEventsUrl(String eventsUrl) {
//		this.eventsUrl = eventsUrl;
//	}
//
//	public String getReceivedEventsUrl() {
//		return receivedEventsUrl;
//	}
//
//	public void setReceivedEventsUrl(String receivedEventsUrl) {
//		this.receivedEventsUrl = receivedEventsUrl;
//	}
//
//	public String getType() {
//		return type;
//	}
//
//	public void setType(String type) {
//		this.type = type;
//	}
//
//	public String getSiteAdmin() {
//		return siteAdmin;
//	}
//
//	public void setSiteAdmin(String siteAdmin) {
//		this.siteAdmin = siteAdmin;
//	}
//
//	@Override
//	public String toString() {
//		return "UserDTO{" +
//				"login='" + login + '\'' +
//				", id=" + id +
//				", avatar_url='" + avatar_url + '\'' +
//				", gravatarId='" + gravatarId + '\'' +
//				", url='" + url + '\'' +
//				", htmlUrl='" + htmlUrl + '\'' +
//				", followersUrl='" + followersUrl + '\'' +
//				", followingUrl='" + followingUrl + '\'' +
//				", gistsUrl='" + gistsUrl + '\'' +
//				", starredUrl='" + starredUrl + '\'' +
//				", subscriptionsUrl='" + subscriptionsUrl + '\'' +
//				", organizationsUrl='" + organizationsUrl + '\'' +
//				", reposUrl='" + reposUrl + '\'' +
//				", eventsUrl='" + eventsUrl + '\'' +
//				", receivedEventsUrl='" + receivedEventsUrl + '\'' +
//				", type='" + type + '\'' +
//				", siteAdmin='" + siteAdmin + '\'' +
//				'}';
//	}
}
