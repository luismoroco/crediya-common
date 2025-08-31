package com.crediya.common.logging.aspect;

import com.crediya.common.logging.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;

@Aspect
public class AutomaticLoggingAspect {

  private final Logger logger;

  public AutomaticLoggingAspect(Logger logger) {
    this.logger = logger;
  }

  @Around("@annotation(AutomaticLogging)")
  public Object logReactiveMethods(ProceedingJoinPoint joinPoint) throws Throwable {
    Object result = joinPoint.proceed();
    if (result instanceof Mono) {
      return ((Mono<?>) result)
        .doOnSubscribe(subscription -> this.logger.info(
          String.format("Starting flow [signature=%s][args=%s]", joinPoint.getSignature(),
            Arrays.toString(joinPoint.getArgs())))
        )
        .doOnError(throwable -> this.logger.error(
          String.format("Error while executing flow [signature=%s][args=%s][error=%s]", joinPoint.getSignature(),
            Arrays.toString(joinPoint.getArgs()), throwable.getMessage()))
        )
        .doOnTerminate(() -> this.logger.info(
          String.format("Flow finished [signature=%s]", joinPoint.getSignature()))
        );
    } else if (result instanceof Flux) {
      return ((Flux<?>) result)
        .doOnSubscribe(subscription -> this.logger.info(
          String.format("Starting flow [signature=%s][args=%s]", joinPoint.getSignature(),
            Arrays.toString(joinPoint.getArgs())))
        )
        .doOnError(throwable -> this.logger.error(
          String.format("Error while executing flow [signature=%s][args=%s][error=%s]", joinPoint.getSignature(),
            Arrays.toString(joinPoint.getArgs()), throwable.getMessage()))
        )
        .doOnTerminate(() -> this.logger.info(
          String.format("Flow finished [signature=%s]", joinPoint.getSignature()))
        );
    }
    return result;
  }
}
