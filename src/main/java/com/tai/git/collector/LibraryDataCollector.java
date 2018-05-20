package com.tai.git.collector;


import com.tai.git.dto.QueryResultDTO;
import com.tai.git.model.Library;
import com.tai.git.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.stream.Stream;

@Service
public class LibraryDataCollector implements Runnable {

	public static final int _6_SECONDS = 6000;

	public static void main(String[] args) {
		new LibraryDataCollector().fetchLibraries("gjasinski");
	}

	private final RestTemplate restTemplate = new RestTemplate();
	private final PomCleaner pomCleaner = new PomCleaner();
	private final PomSearcher pomDownloader = new PomSearcher();
	private UserProviderService userProviderService = new UserProviderService();
	@Autowired
	private LibraryRepository libraryRepository;

	@Override
	public void run() {
		System.out.println("ALA MA KOTA" + userProviderService.getTotalCount());
		for (int i = 0; i < userProviderService.getTotalCount(); i++) {
			try {
				Thread.sleep(_6_SECONDS);
				System.out.println("USER!");
				String user = userProviderService.getNextUser();
				fetchLibraries(user);
			} catch (Exception e) {
				//todo move from stdout to logs
				e.printStackTrace();
			}
		}
	}

	private void fetchLibraries(String user) {
		try {
			System.out.println("FETCHIN " + user);
			QueryResultDTO[] items = pomDownloader.downloadPOMForUser(user);
			Stream<QueryResultDTO> stream = Arrays.stream(items);
			pomCleaner.cleanPom(stream).forEach(this::addToDatabase);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	private void addToDatabase(String dependecy) {
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
				Library library = new Library(groupId, artifactId, version);
				libraryRepository.save(library);
			}

		}
	}
}
