package com.baeldung.arrowoperator

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ArrowOperatorUnitTest {

    @Test
    fun `should return first label in when statement`() {
        val label: Int? = 1
        val returned = when (label) {
            1 -> "First label"
            2 -> "Second label"
            else -> {
                "Label is neither first nor second"
            }
        }
        assertEquals("First label", returned)
    }

    @Test
    fun `should return number multiplication in lambda expression`() {
        val numbers = listOf(1, 2, 3, 4)
        val multiplication = { x: Int, y: Int -> x * y }
        val total = numbers.reduce(multiplication)
        assertEquals(24, total)
    }

    @Test
    fun `should return number multiplication in function type`() {
        val multiplication: (Int, Int) -> Int = { a, b -> a * b }
        val appliedOperation = applyOperation(1, 5, multiplication)
        assertEquals(5, appliedOperation)
    }

    @Test
    fun `should return tripled value of given number`() {
        val triple = createMultiplier(3)
        assertEquals(15, triple(5))
    }

    private fun createMultiplier(factor: Int): (Int) -> Int {
        return { input -> input * factor }
    }

    private fun applyOperation(x: Int, y: Int, operation: (Int, Int) -> Int): Int {
        return operation(x, y)
    }
}