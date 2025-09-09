package com.crediya.common.validation.exc;

import com.crediya.common.exc.BadRequestException;

import java.util.Map;

public class ValidationException extends BadRequestException {
  public ValidationException(String message) {
    super(message);
  }

  public ValidationException(String message, Map<String, Object> body) {
    super(message, body);
  }
}
