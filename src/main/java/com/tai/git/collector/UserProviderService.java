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
	private final RestTemplate restTemplate = new RestTemplate();
	private final List<UserDTO> users = new LinkedList<>();
	private long page;


	String getNextUser() {
		if (users.isEmpty()) {
			fetchUsers();
			page++;
		}
		UserDTO user = users.remove(0);
		return user.getLogin();
	}

	private void fetchUsers() {
		String fooResourceUrl = "https://api.github.com/search/users?q=language:Java&sort=joined&order=asc&page=" + page + "&per_page=100";

		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(fooResourceUrl)
				.queryParam("since", page);


		HttpEntity<?> entity = new HttpEntity<>(headers);
		ResponseEntity<QueryResultsDTO<UserDTO>> exchange = restTemplate.exchange(
				builder.build().toUriString(),
				HttpMethod.GET,
				entity,
				new ParameterizedTypeReference<QueryResultsDTO<UserDTO>>() {
				});
		users.addAll(Arrays.asList(exchange.getBody().getItems()));
	}

}
