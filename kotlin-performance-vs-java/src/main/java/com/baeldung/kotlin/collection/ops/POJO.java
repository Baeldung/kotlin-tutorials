package com.baeldung.kotlin.collection.ops;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Currency;

public class POJO {
    private String fieldA;
    private String fieldB;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private int age;
    private BigDecimal salary;
    private Currency currency;
    private InnerPOJO child;

    public POJO(
            String fieldA, String fieldB,
            String addressLine1, String addressLine2,
            String city, int age, BigDecimal salary, Currency currency, InnerPOJO child
    ) {
        this.fieldA = fieldA;
        this.fieldB = fieldB;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.age = age;
        this.salary = salary;
        this.currency = currency;
        this.child = child;
    }

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

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public InnerPOJO getChild() {
        return child;
    }

    public void setChild(InnerPOJO child) {
        this.child = child;
    }
}

