package com.tai.git.collector;

import com.tai.git.collector.dtos.QueryResultsDTO;
import com.tai.git.collector.dtos.GitUserDTO;
import com.tai.git.models.GithubUser;
import com.tai.git.repositories.GithubUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class UserProviderService {
	private static final String API_GITHUB_SEARCH_USERS_URL = "https://api.github.com/search/users?q=language:Java&sort=joined&order=asc&page=";
	private static final String API_GITHUB_GET_USER_URL = "https://api.github.com/users/";
	private static final String PER_PAGE_100 = "&per_page=100";
	private static final int PER_PAGE = 100;
	private final RestTemplate restTemplate = new RestTemplate();
	private final List<GitUserDTO> users = new LinkedList<>();
	private long page;
	private long totalCount;


	private final GithubUserRepository githubUserRepository;

	@Autowired
	public UserProviderService(GithubUserRepository githubUserRepository) {
		this.githubUserRepository = githubUserRepository;

		System.out.println(page);
		fetchUsers();
	}

	public GitUserDTO getNextUser() {
		GitUserDTO remove;
		Optional<GithubUser> byGithubId;
		do{
			if (users.isEmpty()) {
				fetchUsers();
				page++;
			}
			remove = users.remove(0);
			byGithubId = githubUserRepository.getByGithubId(remove.getId());
		} while (byGithubId.isPresent() && byGithubId.get().isProcessed());

		System.out.println("ZWRAAAACAM:" + remove.getId());
		return remove;
	}

	public GitUserDTO getUserByLogin(String login){
		HttpHeaders headers = buildHeaders();
		HttpEntity<?> entity = new HttpEntity<>(headers);
		return restTemplate.exchange(
				API_GITHUB_GET_USER_URL + login,
				HttpMethod.GET,
				entity,
				new ParameterizedTypeReference<GitUserDTO>() {
				}).getBody();
	}

	private void fetchUsers() {
		long processedUsers = githubUserRepository.countByProcessed(true);
		long page = processedUsers / PER_PAGE;
		HttpHeaders headers = buildHeaders();
		HttpEntity<?> entity = new HttpEntity<>(headers);
		ResponseEntity<QueryResultsDTO<GitUserDTO>> exchange = restTemplate.exchange(
				buildUrl(page),
				HttpMethod.GET,
				entity,
				new ParameterizedTypeReference<QueryResultsDTO<GitUserDTO>>() {
				});
		users.addAll(Arrays.asList(exchange.getBody().getItems()));
		totalCount = exchange.getBody().getTotal_count();
	}

	private String buildUrl(long page) {
		String url = API_GITHUB_SEARCH_USERS_URL + page + PER_PAGE_100;
		return UriComponentsBuilder.fromHttpUrl(url).build().toUriString();
	}

	private HttpHeaders buildHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
		return headers;
	}

	public long getTotalCount() {
		return totalCount;
	}
}
