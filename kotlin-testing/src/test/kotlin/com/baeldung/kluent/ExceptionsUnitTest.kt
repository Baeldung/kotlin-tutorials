package com.baeldung.kluent

import org.amshove.kluent.*
import org.junit.jupiter.api.Test

class ExceptionsUnitTest {
    @Test
    fun `when a block throws then we should be able to assert the correct exception`() {
        invoking { throw IllegalStateException() } shouldThrow IllegalStateException::class
    }

    @Test
    fun `when a block throws then we should be able to assert the correct exception and message`() {
        invoking { throw IllegalStateException("Oops") } shouldThrow IllegalStateException::class withMessage "Oops"
    }
}