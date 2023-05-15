package typeconversion

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class TestIntToChar {
    @Test
    fun `Given an Int, When it's valid number, Then should convert Int to the corresponding Unicode character`() {
        assertEquals('A',  'A')
        // The escaped Unicode representation of 'A'
        assertEquals('\u0041',  'A')
    }

    @Test
    fun `Given a Char, When it's valid char, Then should convert to Int`() {
        assertEquals('A'.code, 65)
        assertEquals('\u0041'.code, 65)
    }
    @Test
    fun `Given an Int, When it's a valid digit, Then should convert to the corresponding Char with the specified radix`() {
        assertEquals(1.digitToChar(2), '1')
        assertThrows<IllegalArgumentException> { 2.digitToChar(2) }

        assertEquals(15.digitToChar(16), 'F')
        assertThrows<IllegalArgumentException> { 16.digitToChar(16) }

        assertEquals(35.digitToChar(36), 'Z')
        assertThrows<IllegalArgumentException> { 36.digitToChar(36) } }
    }