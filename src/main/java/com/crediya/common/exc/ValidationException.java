package com.crediya.common.exc;

import java.util.Map;

public class ValidationException extends BaseException {
  public ValidationException(String message) {
    super(message);
  }

  public ValidationException(String message, Map<String, Object> body) {
    super(message, body);
  }
}
