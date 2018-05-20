package com.tai.git.collector.dtos;

public class QueryResultsDTO<T> {
	private int total_count;
	private boolean incompleteResults;
	private T items[];

	public QueryResultsDTO(int total_count, boolean incompleteResults, T[] items) {
		this.total_count = total_count;
		this.incompleteResults = incompleteResults;
		this.items = items;
	}

	public QueryResultsDTO() {
	}

	public int getTotal_count() {
		return total_count;
	}

	public void setTotal_count(int total_count) {
		this.total_count = total_count;
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
