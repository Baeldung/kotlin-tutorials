package com.baeldung;

public class StringWrapper {
  private final String source;

  public StringWrapper(String source) {
    this.source = source;
  }

  public boolean containsIgnoreCase(String other) {
    if (source == null) {
      return false;
    }

    return source.toLowerCase().contains(other.toLowerCase());
  }

  public String getSource() {
    return source;
  }
}
