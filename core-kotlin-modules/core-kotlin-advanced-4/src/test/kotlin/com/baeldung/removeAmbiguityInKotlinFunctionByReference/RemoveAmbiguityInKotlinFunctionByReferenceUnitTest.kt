package com.baeldung.removeAmbiguityInKotlinFunctionByReference

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class RemoveAmbiguityInKotlinFunctionByReferenceUnitTest {
    class Calculator(val base: Int) {
        fun compute(value: Int): Int = base + value
        fun compute(value: String): String = "$base$value"
    }

    private val calculator = Calculator(10)

    class Converter(val factor: Double) {
        fun convert(value: Int): Double = value * factor
        fun convert(value: String): Double = value.toDouble() * factor
    }

    fun Converter.convertInt(value: Int): Double = convert(value)
    fun Converter.convertString(value: String): Double = convert(value)

    private val converter = Converter(2.5)

    @Test
    fun testConvertInt() {
        assertEquals(10.0, converter.convertInt(4))
    }

    @Test
    fun testConvertString() {
        assertEquals(10.0, converter.convertString("4"))
    }

    @Test
    fun testComputeInt() {
        val computeInt: (Int) -> Int = calculator::compute
        assertEquals(15, computeInt(5))
    }

    @Test
    fun testComputeString() {
        val computeString: (String) -> String = calculator::compute
        assertEquals("105", computeString("5"))
    }

    @Test
    fun testComputeIntWithLambda() {
        val computeInt: (Int) -> Int = { value -> calculator.compute(value) }
        assertEquals(15, computeInt(5))
    }

    @Test
    fun testComputeStringWithLambda() {
        val computeString: (String) -> String = { value -> calculator.compute(value) }
        assertEquals("105", computeString("5"))
    }
}