package com.crediya.common.exc;

import java.util.Map;

public class UnavailableExternalServiceException extends BaseException {
  public UnavailableExternalServiceException(String message, Map<String, Object> body) {
    super(message, body);
  }

  public UnavailableExternalServiceException(String message) {
    super(message);
  }
}
