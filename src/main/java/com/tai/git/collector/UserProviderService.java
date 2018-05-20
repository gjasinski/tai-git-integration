package com.tai.git.collector;

import com.tai.git.dto.QueryResultsDTO;
import com.tai.git.dto.UserDTO;
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

@Service
public class UserProviderService {
	private static final String API_GITHUB_URL = "https://api.github.com/search/users?q=language:Java&sort=joined&order=asc&page=";
	private static final String PER_PAGE_100 = "&per_page=100";
	private static final String ACCEPT_HTTP_HEADER = "Accept";
	private final RestTemplate restTemplate = new RestTemplate();
	private final List<UserDTO> users = new LinkedList<>();
	private long page;
	private long totalCount;


	UserProviderService() {
		fetchUsers();
	}

	UserDTO getNextUser() {
		if (users.isEmpty()) {
			fetchUsers();
			page++;
		}
		return users.remove(0);
	}

	private void fetchUsers() {
		HttpHeaders headers = buildHeaders();
		HttpEntity<?> entity = new HttpEntity<>(headers);
		ResponseEntity<QueryResultsDTO<UserDTO>> exchange = restTemplate.exchange(
				buildUrl(),
				HttpMethod.GET,
				entity,
				new ParameterizedTypeReference<QueryResultsDTO<UserDTO>>() {
				});
		users.addAll(Arrays.asList(exchange.getBody().getItems()));
		totalCount = exchange.getBody().getTotal_count();
	}

	private String buildUrl() {
		String url = API_GITHUB_URL + page + PER_PAGE_100;
		return UriComponentsBuilder.fromHttpUrl(url).build().toUriString();
	}

	private HttpHeaders buildHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.set(ACCEPT_HTTP_HEADER, MediaType.APPLICATION_JSON_VALUE);
		return headers;
	}

	public long getTotalCount() {
		return totalCount;
	}
}
