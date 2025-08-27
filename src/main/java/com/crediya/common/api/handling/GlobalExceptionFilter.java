package com.crediya.common.api.handling;

import com.crediya.common.api.ApiException;
import com.crediya.common.exc.BadRequestException;
import com.crediya.common.exc.BaseException;
import com.crediya.common.exc.NotFoundException;
import com.crediya.common.exc.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.web.reactive.function.server.HandlerFilterFunction;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Map;

public class GlobalExceptionFilter implements HandlerFilterFunction<ServerResponse, ServerResponse> {

  private static final Map<Class<? extends BaseException>, HttpStatus> EXCEPTION_MAP =
    Map.of(
      BadRequestException.class, HttpStatus.BAD_REQUEST,
      NotFoundException.class, HttpStatus.NOT_FOUND,
      ValidationException.class, HttpStatus.BAD_REQUEST
    );

  @Override
  @NonNull
  public Mono<ServerResponse> filter(@NonNull ServerRequest request,
                                     @NonNull HandlerFunction<ServerResponse> next) {
    return next.handle(request)
      .onErrorResume(RuntimeException.class, ex -> {
        HttpStatus status = EXCEPTION_MAP.getOrDefault(ex.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        return ServerResponse.status(status)
          .bodyValue(ApiException.of(ex));
      });
  }
}
