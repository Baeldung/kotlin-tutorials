package com.baeldung.inttohex

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class IntToHexUnitTest {

    @Test
    fun `toHexString should convert int to hex`() {
        val hex = Integer.toHexString(4001)
        assertEquals("fa1", hex)
    }

    @Test
    fun `toString should convert int to hex`() {
        val number = 4001
        assertEquals("fa1", number.toString(16))
    }

    @Test
    @ExperimentalUnsignedTypes
    fun `toHexString performs unsigned conversions`() {
        val number = -100
        assertNotEquals(Integer.toHexString(number), number.toString(16))
        assertEquals(Integer.toHexString(number), number.toUInt().toString(16))
        val unsignedLong = number.toLong() and 0xffffffffL
        assertEquals(Integer.toHexString(number), unsignedLong.toString(16))
    }

    @Test
    fun `format should convert to hex`() {
        val number = 4001
        assertEquals("fa1", "%x".format(number))
    }
}
