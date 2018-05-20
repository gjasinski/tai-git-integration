package com.tai.git.dto;

public class QueryResultDTO {
	private String name;
	private String html_url;

	public QueryResultDTO(String name, String html_url) {
		this.name = name;
		this.html_url = html_url;
	}

	public QueryResultDTO() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHtml_url() {
		return html_url;
	}

	public void setHtml_url(String html_url) {
		this.html_url = html_url;
	}

	@Override
	public String toString() {
		return "QueryResultDTO{" +
				"name='" + name + '\'' +
				", html_url='" + html_url + '\'' +
				'}';
	}
}
