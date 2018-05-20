package com.tai.git.dto;

public class QueryResultsDTO {
	private int totalCount;
	private boolean incompleteResults;
	private QueryResultDTO items[];

	public QueryResultsDTO(int totalCount, boolean incompleteResults, QueryResultDTO[] items) {
		this.totalCount = totalCount;
		this.incompleteResults = incompleteResults;
		this.items = items;
	}

	public QueryResultsDTO() {
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public boolean isIncompleteResults() {
		return incompleteResults;
	}

	public void setIncompleteResults(boolean incompleteResults) {
		this.incompleteResults = incompleteResults;
	}

	public QueryResultDTO[] getItems() {
		return items;
	}

	public void setItems(QueryResultDTO[] items) {
		this.items = items;
	}
}
