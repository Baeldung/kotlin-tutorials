package com.baeldung.nullassertion

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertThrows
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class NotNullAssertionUnitTest {

    @Test
    fun givenNullableValue_WhenNotNull_ShouldExtractTheValue() {
        val answer: String? = "42"

        assertEquals(42, answer!!.toInt())
    }

    @Test
    fun givenNullableValue_WhenIsNull_ThenShouldThrow() {
        assertFailsWith<NullPointerException> {
            val noAnswer: String? = null
                noAnswer!!
        }
    }
}
