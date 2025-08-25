package com.crediya.common.exc;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class BaseException extends RuntimeException {
  private final Map<String, String> body;

  public BaseException(String message, Map<String, String> body) {
    super(message);
    this.body = Optional.ofNullable(body).orElse(new HashMap<>());
  }

  public Map<String, String> getBody() {
    return body;
  }
}

