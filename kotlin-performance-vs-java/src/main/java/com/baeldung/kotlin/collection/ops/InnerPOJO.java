package com.baeldung.kotlin.collection.ops;

public class InnerPOJO {
    private String fieldA;
    private String fieldB;

    public String getFieldA() {
        return fieldA;
    }

    public void setFieldA(String fieldA) {
        this.fieldA = fieldA;
    }

    public String getFieldB() {
        return fieldB;
    }

    public void setFieldB(String fieldB) {
        this.fieldB = fieldB;
    }

    public InnerPOJO(String fieldA, String fieldB) {
        this.fieldA = fieldA;
        this.fieldB = fieldB;
    }
}
