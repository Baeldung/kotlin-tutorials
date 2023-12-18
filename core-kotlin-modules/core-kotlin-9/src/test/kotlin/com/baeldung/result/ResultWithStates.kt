package com.baeldung.result

import org.junit.Assert.*
import org.junit.jupiter.api.Test
class ResultWithStates {
    @Test
    fun `Should handle Successful States`() {
        val result = Result.success(42)
        assertTrue(result.isSuccess)
        assertEquals(42, result.getOrNull())
        result.onSuccess { println(it) }
    }

    @Test
    fun `Should handle Failure States`() {
        val result = Result.failure<Int>(Exception("We have an error!"))
        assertTrue(result.isFailure)
        assertNotNull(result.exceptionOrNull())
        result.onFailure { println(it.message) }
    }
}
