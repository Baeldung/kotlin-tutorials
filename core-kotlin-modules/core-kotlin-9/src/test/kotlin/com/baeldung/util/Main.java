package com.baeldung.util;

public class Main {
    public static void main(String[] args) {
        // Without @JvmStatic
        Utils.INSTANCE.foo();

        // With @JvmStatic
        Utils.foo();
    }
}

