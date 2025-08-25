package com.crediya.common.api.handling;

import com.crediya.common.api.ApiException;
import com.crediya.common.exc.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @org.springframework.web.bind.annotation.ExceptionHandler({BadRequestException.class})
  public Mono<ResponseEntity<ApiException>> badRequestException(BadRequestException exc) {
    return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiException(exc)));
  }
}
