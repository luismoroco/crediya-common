package com.crediya.common.transaction;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface Transaction {
  <TObject> Mono<TObject> init(Mono<TObject> mono);

  <TObject> Flux<TObject> init(Flux<TObject> flux);
}
