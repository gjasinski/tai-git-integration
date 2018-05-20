package com.tai.git.collector;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class PomDownloader {
	String downloadRawPage(String url) {
		try (Scanner scanner = new Scanner(new URL(url).openStream(), StandardCharsets.UTF_8.toString())) {
			scanner.useDelimiter("\\A");
			return scanner.hasNext() ? scanner.next() : "";
		} catch (IOException ex) {
			ex.printStackTrace();
			return "";
		}
	}
}
