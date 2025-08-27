package com.crediya.common;

import java.util.Objects;

public class EmailUtils {

  public static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

  private EmailUtils() {
  }

  public static boolean isValid(String email) {
    return Objects.nonNull(email) && !email.isBlank() && email.matches(EMAIL_REGEX);
  }
}
