package com.baeldung.pair

import org.junit.Test
import kotlin.test.assertEquals

class NullabilityTests {
    @Test
    fun testPrintNameLength() {
        val name: String? = "John"
        val expectedLength = 4
        val actualLength = name?.length
        assertEquals(expectedLength, actualLength)
    }

    @Test
    fun testPrintNonNullValue() {
        val nullableValue: String? = "Hello, World!"
        val expectedValue = "Hello, World!"
        var actualValue: String? = null
        nullableValue?.let {
            actualValue = it
        }
        assertEquals(expectedValue, actualValue)
    }
}
