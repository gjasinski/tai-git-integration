package com.tai.git.dto;

public class QueryResultsDTO<T> {
	private int totalCount;
	private boolean incompleteResults;
	private T items[];

	public QueryResultsDTO(int totalCount, boolean incompleteResults, T[] items) {
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

	public T[] getItems() {
		return items;
	}

	public void setItems(T[] items) {
		this.items = items;
	}
}
