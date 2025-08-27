package com.crediya.common.exc;

import java.util.Map;

public class NotFoundException extends BaseException {
  public NotFoundException(String message, Map<String, Object> body) {
    super(message, body);
  }

  public NotFoundException(String message) {
    super(message);
  }
}
