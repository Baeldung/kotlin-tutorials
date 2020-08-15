package com.baeldung.unsigned

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

@ExperimentalUnsignedTypes
class UnsignedUnitTest {

    @Test
    fun `Unsigned integers support the same set of operations`() {
        assertEquals(3u, 2u + 1u)
        assertEquals(16u, 2u shl 3)
    }

    @Test
    fun `Given a number, When converting, Then should work as expected`() {
        assertEquals((255).toUByte(), (-1).toUByte())
        assertEquals((65535).toUShort(), (-1).toUShort())
        assertEquals(4294967295u, (-1).toUInt())
        assertEquals(18446744073709551615uL, (-1L).toULong())

        assertEquals(-1, (4294967295u).toInt())
    }
}
