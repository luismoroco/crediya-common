package com.crediya.common.mapping;

import java.util.Map;

public interface Mappable<TTarget> {
  default TTarget map() {
    return Mapper.get().map(this, this.getTargetClass());
  }

  default TTarget map(Map<String, Object> overrideKeys) {
    TTarget result = this.map();

    overrideKeys.forEach((key, value) -> {
      try {
        var field = this.getTargetClass().getDeclaredField(key);
        field.setAccessible(true);
        field.set(result, value);
      } catch (NoSuchFieldException | IllegalAccessException e) {
        throw new RuntimeException(e);
      }
    });

    return result;
  }

  Class<TTarget> getTargetClass();
}
