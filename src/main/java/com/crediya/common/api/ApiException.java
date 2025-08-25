package com.crediya.common.api;

import java.sql.Timestamp;
import java.util.Objects;

public class ApiException {
  private String type;
  private String message;
  private Timestamp timestamp;

  public ApiException() {}

  private ApiException(RuntimeException exception) {
    this.type = formatName(exception.getClass().getSimpleName());
    this.message = exception.getMessage();
    this.timestamp = new Timestamp(System.currentTimeMillis());
  }

  public String getType() {
    return this.type;
  }

  public String getMessage() {
    return this.message;
  }

  public Timestamp getTimestamp() {
    return this.timestamp;
  }

  public static ApiException of(RuntimeException exception) {
    return new ApiException(exception);
  }

  private static String formatName(String name) {
    if (Objects.isNull(name) || name.isEmpty()) {
      return name;
    }

    String regex = "([a-z])([A-Z]+)";
    String replacement = "$1_$2";
    return name.replaceAll(regex, replacement).toUpperCase();
  }
}
