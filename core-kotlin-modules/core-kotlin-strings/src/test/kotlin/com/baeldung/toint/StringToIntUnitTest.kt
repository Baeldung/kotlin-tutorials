package com.baeldung.toint

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.NumberFormatException
import kotlin.test.assertEquals
import kotlin.test.assertNull

class StringToIntUnitTest {
    
    @Test
    fun `should convert strings containing valid numeric data to int`() {
        val intValue = "42".toInt()

        assertEquals(42, intValue)
    }

    @Test
    fun `should throw NumberFormatException when the string is not valid number`() {
        assertThrows<NumberFormatException> { "0x2a".toInt() }
        assertThrows<NumberFormatException> { "2.5".toInt() }
        assertThrows<NumberFormatException> { "2.5 inch".toInt() }
        assertThrows<NumberFormatException> { "invalid".toInt() }
    }

    @Test
    fun `should be able to convert hex strings`() {
        val intValue = "2a".toInt(16)

        assertEquals(42, intValue)
    }

    @Test
    fun `orNull variant returns null for invalid data`() {
        assertNull("invalid".toIntOrNull())
        assertEquals(42, "42".toIntOrNull())
        assertEquals(42, "2a".toIntOrNull(16))
    }

    @Test
    @ExperimentalUnsignedTypes
    fun `should convert strings to unsigned integers`() {
        assertEquals(42u, "42".toUInt())
        assertEquals(42u, "2a".toUInt(16))
        assertNull("2a".toUIntOrNull())
        assertEquals(42u, "2a".toUIntOrNull(16))
    }
}
