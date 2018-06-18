package com.tai.git.collector;

import com.tai.git.collector.dtos.QueryResultDTO;
import com.tai.git.collector.dtos.QueryResultsDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class PomSearcher {
	private static final String GUTHUB_API_PREFIX = "https://api.github.com/search/code?q=pom.xml+in:path+user:";
	private final RestTemplate restTemplate = new RestTemplate();

	QueryResultDTO[] downloadPOMForUser(String user) {
		String fooResourceUrl = GUTHUB_API_PREFIX + user;
		HttpHeaders headers = buildHeaders();
		HttpEntity<?> entity = new HttpEntity<>(headers);
		ResponseEntity<QueryResultsDTO<QueryResultDTO>> exchange = restTemplate.exchange(
				fooResourceUrl,
				HttpMethod.GET,
				entity,
				new ParameterizedTypeReference<QueryResultsDTO<QueryResultDTO>>() {
				});
		return exchange.getBody().getItems();
	}

	private HttpHeaders buildHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		return headers;
	}
}
