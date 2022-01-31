package com.baeldung.dataclasses;

public class SimplePojo {
    private final int a;
    private String b;

    SimplePojo(int a, String b) {
        this.a = a;
        this.b = b;
    }

    public int getA() {
        return a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }
}
