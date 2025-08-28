package com.crediya.common.logging;

public interface Logger {
  void trace(String message, Object... args);

  void info(String message, Object... args);

  void warn(String message, Object... args);

  void error(String message, Object... args);
}
