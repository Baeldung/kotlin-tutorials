package com.baeldung.strtolong

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.NumberFormatException

class StringToLongUnitTest {

    @Test
    fun `Given a String, When it's valid number, Then should be able to convert`() {
        val number = "42".toLong()
        assertEquals(42, number)
    }

    @Test
    fun `Given a String, When it's an invalid number, Then should throw an exception`() {
        assertThrows<NumberFormatException> { "the answer is 42".toLong() }
    }

    @Test
    fun `Given a String, When using toLongOrNull, Then should return number or null`() {
        val number: Long? = "42".toLongOrNull()
        assertEquals(42, number)

        assertNull("the answer is 42".toLongOrNull())
    }

    @Test
    fun `Given a String, When converting with Radix, Then works as expected`() {
        assertEquals(15, "f".toLong(16))
        assertEquals(15, "F".toLong(16))
        assertEquals(15, "17".toLong(8))
        assertEquals(15, "1111".toLongOrNull(2))

        assertThrows<NumberFormatException> { "fg".toLong(16) }
        assertNull("8".toLongOrNull(8))
    }

    @Test
    @ExperimentalUnsignedTypes
    fun `Given a String, When converting to unsigned, Then should work as expected`() {
        assertEquals(42uL, "42".toULong())
        assertEquals(15uL, "f".toULong(16))
        assertEquals(15uL, "17".toULongOrNull(8))
        assertNull("179".toULongOrNull(8))
        assertThrows<NumberFormatException> { "179".toULong(8) }
    }

    @Test
    @ExperimentalUnsignedTypes
    fun `Given long values, When using toString, Then should be to convert as expected`() {
        assertEquals("42", 42.toString())
        assertEquals("101010", 42.toString(2))
        assertEquals("2a", 42uL.toString(16))
    }
}
