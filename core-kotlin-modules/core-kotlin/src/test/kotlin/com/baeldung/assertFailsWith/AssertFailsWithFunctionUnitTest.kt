package com.baeldung.assertFailsWith

import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertFailsWith

class AssertFailsWithFunctionUnitTest {
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
        Assert.assertThat(exception.message, CoreMatchers.equalTo("For input string: \"abcdefgh\""))
    }
}