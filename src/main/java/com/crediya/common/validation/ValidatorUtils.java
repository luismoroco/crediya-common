package com.crediya.common.validation;

import com.crediya.common.EmailUtils;
import static com.crediya.common.LogCatalog.*;
import com.crediya.common.PhoneUtils;
import com.crediya.common.exc.ValidationException;

import reactor.core.publisher.Mono;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public class ValidatorUtils {

  private ValidatorUtils() {
  }

  public static Mono<Void> string(Object identifier, String value) {
    return (Objects.isNull(value) || value.isBlank())
      ? Mono.error(new ValidationException(INVALID_PARAMETER.of(identifier, value)))
      : Mono.empty();
  }

  public static Mono<Void> phone(Object identifier, String value) {
    return (!PhoneUtils.isValid(value))
      ? Mono.error(new ValidationException(INVALID_PARAMETER.of(identifier, value)))
      : Mono.empty();
  }

  public static Mono<Void> email(Object identifier, String value) {
    return (!EmailUtils.isValid(value))
      ? Mono.error(new ValidationException(INVALID_PARAMETER.of(identifier, value)))
      : Mono.empty();
  }

  public static Mono<Void> localDate(Object identifier, String value) {
    return string(identifier, value)
      .then(Mono.defer(() -> {
        try {
          LocalDate.parse(value);
          return Mono.empty();
        } catch (DateTimeParseException e) {
          return Mono.error(new ValidationException(INVALID_PARAMETER.of(identifier, value)));
        }
      }));
  }

  public static Mono<Void> nonNull(Object identifier, Object value) {
    return (Objects.isNull(value))
      ? Mono.error(new ValidationException(INVALID_PARAMETER.of(identifier, null)))
      : Mono.empty();
  }

  public static <T extends Comparable<T>> Mono<Void> numberBetween(
    Object identifier,
    T value,
    T start,
    T end
  ) {
    return nonNull(identifier, value)
      .then(Mono.defer(() -> (value.compareTo(start) < 0 || value.compareTo(end) > 0)
        ? Mono.error(new ValidationException(INVALID_PARAMETER.of(identifier, value)))
        : Mono.empty()));
  }
}
