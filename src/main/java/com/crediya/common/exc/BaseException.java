package com.crediya.common.exc;

import java.util.HashMap;
import java.util.Map;

public class BaseException extends RuntimeException {
  private final Map<String, Object> body;

  public BaseException(String message, Map<String, Object> body) {
    super(message);
    this.body = body;
  }

  public BaseException(String message) {
    this(message, new HashMap<>());
  }

  public Map<String, Object> getBody() {
    return body;
  }
}

