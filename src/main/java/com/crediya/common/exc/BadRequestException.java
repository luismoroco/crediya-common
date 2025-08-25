package com.crediya.common.exc;

import java.util.Map;

public class BadRequestException extends BaseException {
  public BadRequestException(String message, Map<String, String> body) {
    super(message, body);
  }

  public BadRequestException(String message) {
    super(message);
  }
}
