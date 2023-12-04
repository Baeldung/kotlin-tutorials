package com.baeldung.result

import org.junit.Assert.assertEquals
import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith

fun transformStringToInt(input: String): Result<Int> {
    return input.toIntOrNull()
        ?.let { Result.success<Int>(it) }
        ?: Result.failure(Exception("Error transforming string to int"))
}


fun divide(a: Int, b: Int): Result<Int> = if (b != 0) {
    Result.success(a / b)
} else {
    Result.failure(Exception("Division by zero is not allowed."))
}


class ResultClassTests {

    @Test
    fun `should Transform Valid String To Int`() {
        val result = transformStringToInt("123")
        assertEquals(Result.success(123), result)
    }

    @Test
    fun `should Fail To Transform Invalid String To Int`() {
        val result = transformStringToInt("abc")
        val expectedException = assertFailsWith<Exception> {
            result.getOrThrow()
        }
        assertEquals("Error transforming string to int", expectedException.message)
    }


    @Test
    fun `should test valid division`() {
        val firstResult = divide(10, 2)
        assertEquals(Result.success(5), firstResult)
    }
    @Test
    fun ` should handle division by zero`(){
        val result = divide(10, 0)
        val expectedException = assertFailsWith<Exception> {
            result.getOrThrow()
        }
        assertEquals("Division by zero is not allowed.", expectedException.message)
    }

}
