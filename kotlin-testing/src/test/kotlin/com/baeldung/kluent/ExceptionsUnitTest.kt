package com.baeldung.kluent

import kotlinx.coroutines.test.runTest
import org.amshove.kluent.coInvoking
import org.amshove.kluent.invoking
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test
import java.util.concurrent.TimeoutException

class ExceptionsUnitTest {
    @Test
    fun `when a block throws then we should be able to assert the correct exception`() {
        invoking { 1 / 0 } shouldThrow ArithmeticException::class
    }

    @Test
    fun `when a block throws then we should be able to assert the correct exception and message`() {
        invoking { 1 / 0 } shouldThrow ArithmeticException::class withMessage "/ by zero"
    }

    @Test
    fun `when a suspend block throws then we should be able to assert the correct exception`() {
        runTest {
            coInvoking { suspend { throw TimeoutException() }() } shouldThrow TimeoutException::class
        }
    }
}