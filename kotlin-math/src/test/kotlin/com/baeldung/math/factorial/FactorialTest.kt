package com.baeldung.math.factorial

import com.baeldung.factorial.factorialIterative
import com.baeldung.factorial.factorialRecursive
import com.baeldung.factorial.factorialTailRecursive
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class FactorialTest {
    @Test
    fun `Should calculate a factorial recursively`() {
        val result = factorialRecursive(7)
        assertEquals(7 * 6 * 5 * 4 * 3 * 2 * 1, result, "7! should be exactly 5040")
    }

    @Test
    fun `Should calculate a factorial tail-recursively`() {
        val result = factorialTailRecursive(6)
        assertEquals(6 * 5 * 4 * 3 * 2 * 1, result, "6! should be exactly 720")
    }

    @Test
    fun `Should calculate a factorial iteratively`() {
        val result = factorialIterative(5)
        assertEquals(5 * 4 * 3 * 2 * 1, result, "5! should be exactly 120")
    }
}