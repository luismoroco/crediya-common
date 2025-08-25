package com.crediya.common.exc;

import java.util.Map;

public class NotFoundException extends BaseException {
  public NotFoundException(String message, Map<String, String> body) {
    super(message, body);
  }
}
