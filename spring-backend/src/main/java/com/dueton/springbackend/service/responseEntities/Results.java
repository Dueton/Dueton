package com.dueton.springbackend.service.responseEntities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Results<T> {
  private int resultCount;
  private T[] results;

  public int getResultCount() {
    return resultCount;
  }

  public void setResultCount(int resultCount) {
    this.resultCount = resultCount;
  }

  public T[] getResults() {
    return results;
  }

  public void setResults(T[] results) {
    this.results = results;
  }
}
