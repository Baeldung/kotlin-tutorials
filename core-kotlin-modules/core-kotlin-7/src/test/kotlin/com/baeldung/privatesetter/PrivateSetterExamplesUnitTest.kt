package com.baeldung.privatesetter

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class PrivateSetterExamplesUnitTest {

    @Test
    fun `bankAccountExampleSuccess - should be executable without exceptions`() {
        val finalBalance = PrivateSetterExamples.bankAccountExampleSuccess()
        assertEquals(50, finalBalance)
    }

    @Test
    fun `loggingBankAccountExample - should be executable without exceptions`() {
        val finalBalance = PrivateSetterExamples.loggingBankAccountExample()
        assertEquals(50, finalBalance)
    }

    @Test
    fun `bankAccountExampleException - should raise illegal ar`() {
        assertThrows<IllegalArgumentException> {
            PrivateSetterExamples.bankAccountExampleException()
        }
    }

}