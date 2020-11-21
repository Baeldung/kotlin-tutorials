package com.baeldung.assertFailsWith

import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith

class AssertFailsWithFunctionTest {

    private val classUnderTest: AssertFailsWithFunction = AssertFailsWithFunction()

    @Test
    fun givenIllegalArgument_thenThrowsException() {
        assertFailsWith<ArrayIndexOutOfBoundsException>(
                message = "Array index is out of bound",
                block = { classUnderTest.assertFailsWithMessage() }
        )
    }

    @Test
    fun givenInvalidFormat_thenThrowsException() {
        assertFailsWith(
                exceptionClass = NumberFormatException::class,
                block = { classUnderTest.assertFailsWithExceptionClass() }
        )
    }

    @Test
    fun givenInvalidOperation_thenThrowsException() {
        assertFailsWith(
                exceptionClass = ArithmeticException::class,
                message = "Invalid arithmetic operation",
                block = { classUnderTest.assertFailsWithMessageAndExceptionClass() })
    }
}
