package com.tai.git;

import com.tai.git.collector.LibraryDataCollector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GitApplication {

	private final LibraryDataCollector libraryDataCollector;

	@Autowired
	public GitApplication(LibraryDataCollector libraryDataCollector) {
		this.libraryDataCollector = libraryDataCollector;
		Thread t = new Thread(libraryDataCollector);
		t.start();
	}

	public static void main(String[] args) {
		SpringApplication.run(GitApplication.class, args);
	}


}
