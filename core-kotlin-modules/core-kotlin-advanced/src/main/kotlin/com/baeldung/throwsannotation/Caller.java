package com.baeldung.throwsannotation;

import java.io.IOException;

public class Caller {

    public static void main(String[] args) {
        unchecked();
    }

    public static void unchecked() {
        try {
            ThrowsKt.throwJavaUnchecked();
        } catch (IllegalArgumentException e) {
            System.out.println("Caught something!");
        }
    }

    public static void checked() throws IOException {
        ThrowsKt.throwJavaChecked();
    }
}
