package com.crediya.common.api.handling;

import com.crediya.common.api.ApiException;
import com.crediya.common.exc.BadRequestException;
import com.crediya.common.exc.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler({ BadRequestException.class })
  public Mono<ResponseEntity<ApiException>> badRequestException(BadRequestException exception) {
    return makeResponse(HttpStatus.BAD_REQUEST, exception);
  }

  @ExceptionHandler({ NotFoundException.class })
  public Mono<ResponseEntity<ApiException>> notFoundException(NotFoundException exception) {
    return makeResponse(HttpStatus.NOT_FOUND, exception);
  }

  @ExceptionHandler({ RuntimeException.class })
  public Mono<ResponseEntity<ApiException>> handleRuntimeException(RuntimeException exception) {
    return makeResponse(HttpStatus.INTERNAL_SERVER_ERROR, exception);
  }

  private static Mono<ResponseEntity<ApiException>> makeResponse(HttpStatus httpStatus, RuntimeException exception) {
    return Mono.just(ResponseEntity.status(httpStatus).body(ApiException.of(exception)));
  }
}
