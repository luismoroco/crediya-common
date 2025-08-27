package com.crediya.common.transaction;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface Transaction {
  <T> Mono<T> init(Mono<T> mono);

  <T> Flux<T> init(Flux<T> flux);
}
