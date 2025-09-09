package com.crediya.common.validation;

import com.crediya.common.validation.exc.ValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.stream.Collectors;

public class ObjectValidator {
  private final Validator validator;
  private static ObjectValidator instance;

  public ObjectValidator(Validator validator) {
    this.validator = validator;

    if (Objects.nonNull(ObjectValidator.instance)) {
      return;
    }
    ObjectValidator.instance = this;
  }

  public <T> Mono<T> validate(T obj) {
    return Mono.fromCallable(() -> validator.validate(obj))
      .flatMap(violations -> violations.isEmpty()
        ? Mono.just(obj)
        : Mono.error(new ValidationException(
          "Validation error",
          violations.stream()
            .collect(Collectors.toMap(
              v -> v.getPropertyPath().toString(),
              ConstraintViolation::getMessage
            ))
        )
      ));
  }

  public static ObjectValidator get() {
    return ObjectValidator.instance;
  }
}
