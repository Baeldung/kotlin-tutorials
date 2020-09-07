package com.baeldung.varargs

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class VarargUnitTest {

    @Test
    fun `Given multiple numbers, When Vararg, Then should return the sum`() {
        val zeroNumbers = sum()
        assertEquals(0, zeroNumbers)

        assertEquals(2, sum(2))
        assertEquals(12, sum(2, 4, 6))
    }

    @Test
    fun `Given an array, When the function is vararg, Then can spread`() {
        val numbers = intArrayOf(1, 2, 3, 4)
        val summation = sum(*numbers)
        assertEquals(10, summation)
    }
}