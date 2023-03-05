package com.baeldung.accidental_override;

public interface Animal {
    default String getSound() { return ""; }
}
