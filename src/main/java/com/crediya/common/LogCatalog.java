package com.crediya.common;

public enum LogCatalog {
  ENTITY_NOT_FOUND {
    @Override
    public String get(String entityName) {
      return String.format("%s not found", entityName);
    }

    @Override
    public String get(String entityName, Object value) {
      return String.format("%s %s not found", value, entityName);
    }
  },
  INVALID_PARAMETER {
    @Override
    public String get(String entityName) {
      return String.format("Invalid parameter: %s", entityName);
    }

    @Override
    public String get(String entityName, Object value) {
      return String.format("%s %s invalid parameter", value, entityName);
    }
  },
  ENTITY_ALREADY_EXISTS {
    @Override
    public String get(String entityName) {
      return String.format("%s already exists", entityName);
    }

    @Override
    public String get(String entityName, Object value) {
      return String.format("%s %s already exists", value, entityName);
    }
  },
  BEGIN_PROCESSING {
    @Override
    public String get(String entityName) {
      return String.format("Begin Processing: %s", entityName);
    }

    @Override
    public String get(String entityName, Object value) {
      return String.format("Begin Processing: %s with %s", entityName, value);
    }
  },
  ERROR_PROCESSING {
    @Override
    public String get(String entityName) {
      return String.format("Error Processing: %s", entityName);
    }

    @Override
    public String get(String entityName, Object value) {
      return String.format("Error Processing: %s with %s", entityName, value);
    }
  },
  ENTITY_FOUND {
    @Override
    public String get(String entityName) {
      return String.format("%s found", entityName);
    }

    @Override
    public String get(String entityName, Object value) {
      return String.format("%s %s found", entityName, value);
    }
  },
  SUCCESSFUL_PROCESSING {
    @Override
    public String get(String entityName) {
      return String.format("Successful Processing: %s", entityName);
    }

    @Override
    public String get(String entityName, Object value) {
      return String.format("Successful Processing: %s with %s", entityName, value);
    }
  };

  public abstract String get(String entityName);

  public abstract String get(String entityName, Object value);
}
