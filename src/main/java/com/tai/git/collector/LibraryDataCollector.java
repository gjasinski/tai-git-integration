package com.tai.git.collector;


import com.tai.git.model.Library;
import com.tai.git.dto.QueryResultDTO;
import com.tai.git.dto.QueryResultsDTO;
import com.tai.git.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;

@Service
public class LibraryDataCollector implements Runnable {
	public static void main(String[] args) {
		new LibraryDataCollector().fetchLibraries("gjasinski");
	}

	private final RestTemplate restTemplate = new RestTemplate();
	private UserProviderService userProviderService = new UserProviderService();
	@Autowired
	private LibraryRepository libraryRepository;

	@Override
	public void run() {
//		try {
//			Thread.sleep(10000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		for (int i = 0; i < 5000; i++) {
			try {
				Thread.sleep(6000);
				String user = userProviderService.getNextUser();
				System.out.println("FETCHIN USER: " + user);
				fetchLibraries(user);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	void fetchLibraries(String user) {
		String fooResourceUrl = "https://api.github.com/search/code?q=pom.xml+in:path+user:" + user;

		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

		System.out.println(fooResourceUrl);
		HttpEntity<?> entity = new HttpEntity<>(headers);
		ResponseEntity<QueryResultsDTO> exchange = restTemplate.exchange(
				fooResourceUrl,
				HttpMethod.GET,
				entity,
				QueryResultsDTO.class);

		QueryResultDTO[] items = exchange.getBody().getItems();
		Arrays.stream(items).map(QueryResultDTO::getHtml_url)
				.map(url -> url.replace("blob", "raw"))
				.map(this::downloadRawPage)
				.map(pom -> pom.substring(pom.indexOf("<dependencies>") + 14, pom.indexOf("</dependencies>")))
				.flatMap(dependencies -> Arrays.stream(dependencies.split("</dependency>")))
				//.map(dependency -> dependency.substring(12))
				.peek(s -> System.out.println(s))
				.forEach(this::addToDatabase);


	}

	String downloadRawPage(String url) {
		try (Scanner scanner = new Scanner(new URL(url).openStream(), StandardCharsets.UTF_8.toString())) {
			scanner.useDelimiter("\\A");
			return scanner.hasNext() ? scanner.next() : "";
		} catch (IOException ex) {
			return "";
		}
	}

	void addToDatabase(String dependecy) {
		if (dependecy.contains("<version>") && dependecy.contains("<artifactId>") && dependecy.contains("<groupId>")) {
			String groupId = dependecy.substring(dependecy.indexOf("<groupId>") + 9, dependecy.indexOf("</groupId>"));
			String artifactId = dependecy.substring(dependecy.indexOf("<artifactId>") + 12, dependecy.indexOf("</artifactId>"));
			String version = dependecy.substring(dependecy.indexOf("<version>") + 9, dependecy.indexOf("</version>"));
			System.out.println(groupId);
			System.out.println(artifactId);
			System.out.println(version);
			Library fetched = libraryRepository.findByGroupIdEqualsAndArtifactIdEqualsAndVersionEquals(groupId, artifactId, version);
			if (fetched != null) {
				fetched.setCounter(fetched.getCounter() + 1);
				libraryRepository.save(fetched);
			} else {
				Library library = new Library(groupId, artifactId, version, 1);
				libraryRepository.save(library);
				System.out.println("CREATED AND ADDED " + library.toString());
			}

		}
	}
}
