package typeconversion

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class TestIntToChar {

    @Test
    fun `Given an Int, When it's valid number, Then should convert Int to the corresponding Unicode character`() {
        assertEquals('A', 65.toChar())
        // The escaped Unicode representation of 'A'
        assertEquals('\u0041', 65.toChar())
    }

    @Test fun `Given an Int, When it's a valid digit, Then should convert to the corresponding Char`() {
        assertEquals('0', 0.digitToChar())
        assertEquals('9', 9.digitToChar())

        assertThrows<IllegalArgumentException> { (-1).digitToChar() }
        assertThrows<IllegalArgumentException> { 10.digitToChar() }
    }

    @Test
    fun `Given an Int, When it's a valid digit, Then should convert to the corresponding Char with the specified radix`() {
        assertEquals('1', 1.digitToChar(2))
        assertThrows<IllegalArgumentException> { 2.digitToChar(2) }

        assertEquals('F', 15.digitToChar(16))
        assertThrows<IllegalArgumentException> { 16.digitToChar(16) }

        assertEquals('Z', 35.digitToChar(36))
        assertThrows<IllegalArgumentException> { 36.digitToChar(36) }
    }

}