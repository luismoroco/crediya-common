package com.crediya.common.mapping;

import org.modelmapper.ModelMapper;

import java.util.Objects;

public class Mapper {
  private final ModelMapper mapper;
  private static Mapper instance;

  public Mapper(ModelMapper mapper) {
    this.mapper = mapper;

    if (Objects.nonNull(Mapper.instance)) {
      return;
    }
    Mapper.instance = this;
  }

  public static Mapper get() {
    return Mapper.instance;
  }

  public <TSource, TTarget> TTarget map(TSource source, Class<TTarget> targetClass) {
    return mapper.map(source, targetClass);
  }
}
