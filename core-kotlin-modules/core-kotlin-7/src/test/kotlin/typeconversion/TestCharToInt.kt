package typeconversion

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertNull

class TestCharToInt {

    @Test
    fun `Given a Char, When it's a valid Unicode character, Then should convert to the corresponding Int`() {
        assertEquals('A'.code, 65)
        assertEquals('\u0041'.code, 65)
    }

    @Test
    fun `Given a Char, When it's a valid digit, Then should convert to the corresponding Int`() {
        assertEquals('0'.digitToInt(), 0)
        assertEquals('9'.digitToInt(), 9)

        assertThrows<IllegalArgumentException> { 'A'.digitToInt() }
        assertNull('A'.digitToIntOrNull())
    }

    @Test
    fun `Given a Char, When it's a valid digit, Then should convert to the corresponding Int with the specified radix`() {
        assertEquals('1'.digitToInt(2), 1)
        // Not a binary digit
        assertNull('2'.digitToIntOrNull(2))

        assertEquals('F'.digitToInt(16), 15)
        // Not a Hexadecimal digit
        assertNull('G'.digitToIntOrNull(16))

        assertEquals('Z'.digitToInt(36), 35)
        // Not a Hexatrigesimal digit
        assertNull('@'.digitToIntOrNull(36))
    }

}