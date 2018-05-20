package com.tai.git.collector;

import com.tai.git.dtos.QueryResultDTO;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Stream;

class PomCleaner {
	private static final String EMPTY_DEPENDENCIES = "<dependencies></dependencies>";
	private static final String DEPENDENCY_CLOSING_TAG = "</dependency>";
	private static final String DEPENDENCIES_OPENING_TAG = "<dependencies>";
	private static final String DEPENDENCIES_CLOSING_TAG = "</dependencies>";
	private static final String BLOB = "blob";
	private static final String RAW = "raw";
	private final PomDownloader pomDownloader = new PomDownloader();

	Stream<String> cleanPom(Stream<QueryResultDTO> results) {
		return results.map(QueryResultDTO::getHtml_url)
				.map(this::replaceBlobWithRaw)
				.peek(System.out::println)//todo move this from stdout to logs
				.map(pomDownloader::downloadRawPage)
				.filter(pom -> !containsEmptyDependencies(pom))
				.filter(this::containsDependenciesTag)
				.map(this::extractDependecies)
				.flatMap(splitDependencies());
	}

	private Function<String, Stream<? extends String>> splitDependencies() {
		return dependencies -> Arrays.stream(dependencies.split(DEPENDENCY_CLOSING_TAG));
	}

	private String extractDependecies(String pom) {
		return pom.substring(pom.indexOf(DEPENDENCIES_OPENING_TAG) + 14, pom.indexOf(DEPENDENCIES_CLOSING_TAG));
	}

	private boolean containsDependenciesTag(String pom) {
		return pom.contains(DEPENDENCIES_OPENING_TAG);
	}

	private boolean containsEmptyDependencies(String pom) {
		return pom.contains(EMPTY_DEPENDENCIES);
	}

	private String replaceBlobWithRaw(String url) {
		return url.replace(BLOB, RAW);
	}
}
