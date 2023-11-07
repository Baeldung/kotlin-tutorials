package com.baeldung.accidentalOverride;

public interface Animal {
    default String getSound() { return ""; }
}
