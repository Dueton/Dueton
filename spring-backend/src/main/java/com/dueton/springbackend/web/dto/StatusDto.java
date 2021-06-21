package com.dueton.springbackend.web.dto;

public class StatusDto<T> {

  private final String message;
  private final Boolean successful;
  private final T value;

  public StatusDto(String message, Boolean success, T value) {
    this.message = message;
    this.successful = success;
    this.value = value;
  }

  public StatusDto(String message, Boolean success) {
    this(message, success, null);
  }

  public String getMessage() {
    return message;
  }

  public Boolean getSuccessful() {
    return successful;
  }

  public T getValue() {
    return value;
  }
}
