package com.baeldung.result

import org.junit.Assert.assertEquals
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

class RunCatchingTests{

    fun divide(a: Int, b: Int): Result<Int> {
        return runCatching {
            a / b
        }
    }


    @Test
    fun `should handle successful division`() {
        val resultValid = divide(10, 2)
        assertTrue(resultValid.isSuccess)
        assertEquals(5, resultValid.getOrNull())
    }

    @Test
    fun `should handle division by zero`() {
        val resultInvalid = divide(5, 0)
        assertTrue(resultInvalid.isFailure)
        assertEquals(ArithmeticException::class, resultInvalid.exceptionOrNull()!!::class)
        assertEquals("/ by zero", resultInvalid.exceptionOrNull()!!.message)
    }
}

