package com.baeldung.preconditions

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class PreconditionsTest {

    @Test
    fun `printPositiveNumber throws exception for negative number`() {
        val num = -1
        val exception = assertThrows<IllegalArgumentException> {
            printPositiveNumber(num)
        }
        assert(exception.message == "Number must be positive")
    }

    @Test
    fun `printPositiveNumber does not throw exception for positive number`() {
        val num = 1
        printPositiveNumber(num)
    }

    @Test
    fun `printListSize throws exception for list with wrong size`() {
        val list = listOf(1, 2, 3)
        val size = 4
        val exception = assertThrows<IllegalStateException> {
            printListSize(list, size)
        }
        assert(exception.message == "List must contain $size elements")
    }

    @Test
    fun `printListSize does not throw exception for list with correct size`() {
        val list = listOf(1, 2, 3)
        val size = 3
        printListSize(list, size)
    }
}
