package typeconversion

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertNull

class TestCharToInt {

    @Test
    fun `Given a Char, When it's a valid Unicode character, Then should convert to the corresponding Int`() {
        assertEquals(65, 'A'.code)
        assertEquals(65, '\u0041'.code)
    }

    @Test
    fun `Given a Char, When it's a valid digit, Then should convert to the corresponding Int`() {
        assertEquals(0, '0'.digitToInt())
        assertEquals(9, '9'.digitToInt())

        assertThrows<IllegalArgumentException> { 'A'.digitToInt() }
        assertNull('A'.digitToIntOrNull())
    }

    @Test
    fun `Given a Char, When it's a valid digit, Then should convert to the corresponding Int with the specified radix`() {
        assertEquals(1, '1'.digitToInt(2))
        // Not a binary digit
        assertNull('2'.digitToIntOrNull(2))

        assertEquals(15, 'F'.digitToInt(16))
        // Not a Hexadecimal digit
        assertNull('G'.digitToIntOrNull(16))

        assertEquals(35, 'Z'.digitToInt(36))
        // Not a Hexatrigesimal digit
        assertNull('@'.digitToIntOrNull(36))
    }

}