package com.baeldung.kotlin

import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test

class SealedUnitTest {
    fun divide(a: Int, b: Int) : Result<Float, String> = when (b) {
        0 -> Failure("Division by zero")
        else -> Success(a.toFloat() / b)
    }

    @Test
    fun testSuccess() {
        val result = divide(10, 5)
        assertEquals(Success<Float, String>(2.0f), result)
    }

    @Test
    fun testError() {
        val result = divide(10, 0)
        assertEquals(Failure<Float, String>("Division by zero"), result)
    }

    @Test
    fun testMatchOnSuccess() {
        val result = divide(10, 5)
        when (result) {
            is Success -> {
                // Expected
            }
            is Failure -> fail("Expected Success")
        }
    }

    @Test
    fun testMatchOnError() {
        val result = divide(10, 0)
        when (result) {
            is Failure -> {
                // Expected
            }
        }
    }

    @Test
    fun testGetSuccess() {
        val result = divide(10, 5)
        assertEquals(2.0f, result.get())
    }

    @Test
    fun testGetError() {
        val result = divide(10, 0)
        assertNull(result.get())
    }

    @Test
    fun testMapOnSuccess() {
        val result = divide(10, 5)
                .map { "Result: $it" }
        assertEquals(Success<String, String>("Result: 2.0"), result)
    }

    @Test
    fun testMapOnError() {
        val result = divide(10, 0)
                .map { "Result: $it" }
        assertEquals(Failure<Float, String>("Division by zero"), result)
    }

    @Test
    fun testMapFailureOnSuccess() {
        val result = divide(10, 5)
                .mapFailure { "Failure: $it" }
        assertEquals(Success<Float, String>(2.0f), result)
    }

    @Test
    fun testMapFailureOnError() {
        val result = divide(10, 0)
                .mapFailure { "Failure: $it" }
        assertEquals(Failure<Float, String>("Failure: Division by zero"), result)
    }
}
