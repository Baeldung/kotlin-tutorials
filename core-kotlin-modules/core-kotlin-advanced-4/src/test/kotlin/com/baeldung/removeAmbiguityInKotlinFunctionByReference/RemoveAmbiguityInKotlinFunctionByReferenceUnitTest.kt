package com.baeldung.removeAmbiguityInKotlinFunctionByReference

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


typealias StringComputer = (String) -> String

typealias IntComputer = (Int) -> Int

class RemoveAmbiguityInKotlinFunctionByReferenceUnitTest {
    class Calculator(val base: Int) {
        fun compute(value: Int): Int = base + value
        fun compute(value: String): String = "$base$value"
    }

    fun Calculator.computeInt(value: Int): Int = compute(value)

    fun Calculator.computeString(value: String): String = compute(value)

    private val calculator = Calculator(10)

    @Test
    fun `Should compute with int function`() {
        val computeInt: (Int) -> Int = calculator::compute
        assertEquals(15, computeInt(5))
    }

    @Test
    fun `Should compute with string function`() {
        val computeString: (String) -> String = calculator::compute
        assertEquals("105", computeString("5"))
    }

    @Test
    fun `Should compute int with lambda`() {
        val computeInt = { value: Int -> calculator.compute(value) }
        assertEquals(15, computeInt(5))
    }

    @Test
    fun `Should compute string with lambda`() {
        val computeString = { value: String -> calculator.compute(value) }
        assertEquals("105", computeString("5"))
    }

    @Test
    fun `should compute int with extension function`() {
        val computeInt = calculator.computeInt(5)
        assertEquals(15, computeInt)
    }

    @Test
    fun `should compute string with extension function`() {
        val computeString = calculator.computeString("5")
        assertEquals("105", computeString)
    }


    @Test
    fun `Should compute int with type alias`() {
        val computeInt: IntComputer = calculator::compute
        assertEquals(15, computeInt(5))
    }

    @Test
    fun `Should compute string with type alias`() {
        val computeString: StringComputer = calculator::compute
        assertEquals("105", computeString("5"))
    }
}