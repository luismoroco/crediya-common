package com.crediya.common.exc;

import java.util.Map;

public class BadRequestException extends BaseException {
  public BadRequestException(String message, Map<String, Object> body) {
    super(message, body);
  }

  public BadRequestException(String message) {
    super(message);
  }
}
