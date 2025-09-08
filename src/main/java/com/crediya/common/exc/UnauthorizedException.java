package com.crediya.common.exc;

import java.util.Map;

public class UnauthorizedException extends BaseException {
  public UnauthorizedException(String message, Map<String, Object> body) {
    super(message, body);
  }

  public UnauthorizedException(String message) {
    super(message);
  }
}
