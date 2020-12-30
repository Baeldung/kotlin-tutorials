package com.baeldung.kotlin.multiplatform;

public class ExtendedCalculator {

    public static Double square(Double number) {
        return CalculatorKt.multiply(number, number);
    }

}
