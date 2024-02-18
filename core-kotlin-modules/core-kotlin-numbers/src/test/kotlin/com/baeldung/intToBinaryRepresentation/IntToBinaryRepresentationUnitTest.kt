package com.baeldung.intToBinaryRepresentation

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

fun Int.to32BitBinaryString(): String =
    toUInt().toString(2).padStart(Int.SIZE_BITS, '0')

class IntToBinaryRepresentationUnitTest {
    @Test
    fun `when using Integer's toBinaryString() then get expected result`() {
        assertEquals("10110", Integer.toBinaryString(22))
        assertEquals("101010", Integer.toBinaryString(42))
        assertEquals("11111111111111111111111111111110", Integer.toBinaryString(-2))
    }

    @Test
    fun `when using kotlin's toString(2) then get expected result`() {
        assertEquals("10110", 22.toString(radix = 2))
        assertEquals("101010", 42.toString(2)) // we can omit the parameter name "radix"

        assertEquals("-10", (-2).toString(2))
        assertEquals("11111111111111111111111111111110", (-2).toUInt().toString(2))

        assertEquals("10110", 22.toUInt().toString(radix = 2))
        assertEquals("101010", 42.toUInt().toString(2))
    }

    @Test
    fun `when using to32BitBinaryString then get expected result`() {
        assertEquals("00000000000000000000000000010110", 22.to32BitBinaryString())
        assertEquals("00000000000000000000000000101010", 42.to32BitBinaryString())
        assertEquals("11111111111111111111111111111110", (-2).to32BitBinaryString())
    }
}