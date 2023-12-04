package com.baeldung.result

import org.junit.Assert.assertEquals
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class RunCatchingTests{

    fun divide(a: Int, b: Int): Result<Int> {
        return runCatching {
            if (b == 0) {
                throw IllegalArgumentException("Cannot divide by zero")
            }
            a / b
        }
    }

    @Test
    fun `should handle successful division`() {
        val result: Result<Int> = divide(10, 2)
        result.fold(
            onSuccess = { value ->
                assertEquals(5, value, "Result should be 5")
            },
            onFailure = { exception ->
                assertTrue(false, "Should not have thrown an exception: ${exception.message}")
            }
        )
    }

    @Test
    fun `should handle division by zero`() {
        val result: Result<Int> = divide(10, 0)
        result.fold(
            onSuccess = { value ->
                assertTrue(false, "Should have thrown an exception. Got result: $value")
            },
            onFailure = { exception ->
                assertTrue(exception is IllegalArgumentException, "Expected IllegalArgumentException")
                assertEquals("Cannot divide by zero", exception.message, "Unexpected error message")
            }
        )
    }
}

