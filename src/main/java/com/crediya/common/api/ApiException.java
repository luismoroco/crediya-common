package com.crediya.common.api;

import com.crediya.common.exc.BaseException;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ApiException {
  private String type;
  private String message;
  private Timestamp timestamp;
  private Map<String, Object> body;

  public ApiException() {}

  private ApiException(RuntimeException exception) {
    this.type = formatName(exception.getClass().getSimpleName());
    this.message = exception.getMessage();
    this.timestamp = new Timestamp(System.currentTimeMillis());
    this.body = exception instanceof BaseException
      ? ((BaseException) exception).getBody()
      : new HashMap<>();
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

  public Map<String, Object> getBody() {
    return this.body;
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
