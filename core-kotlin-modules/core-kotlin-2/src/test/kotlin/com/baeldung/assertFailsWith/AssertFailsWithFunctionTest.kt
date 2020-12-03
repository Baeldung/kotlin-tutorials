package com.baeldung.assertFailsWith

import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertFailsWith

class AssertFailsWithFunctionTest {

    private val classUnderTest: AssertFailsWithFunction = AssertFailsWithFunction()

    @Test
    fun whenInvalidArray_thenThrowsException() {
        assertThrows<ArrayIndexOutOfBoundsException> {
            classUnderTest.assertFailsWithMessage()
        }
    }

    @Test
    fun givenInvalidArray_thenThrowsException() {
        assertFailsWith<ArrayIndexOutOfBoundsException>(
                message = "No exception found",
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
                message = "No exception found",
                block = { classUnderTest.assertFailsWithMessageAndExceptionClass() }
        )
    }

    @Test
    fun givenInvalidNumericFormat_thenThrowsException() {
        val exception = assertFailsWith<NumberFormatException>(
                block = { Integer.parseInt("abcdefgh") }
        )
        assertThat(exception.message, equalTo("For input string: \"abcdefgh\""))
    }
}
