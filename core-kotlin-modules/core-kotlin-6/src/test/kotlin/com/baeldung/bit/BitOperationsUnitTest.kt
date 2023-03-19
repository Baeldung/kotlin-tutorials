package com.baeldung.bit

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class BitOperationsUnitTest {
    /*
        Logical Bit Operations
     */
    @Test
    fun `applies and bit operator on two integers`() {
        val a = 0b00011110
        val b = 0b01111000
        assertEquals(0b00011000, a and b)
    }

    @Test
    fun `applies or bit operator on two integers`() {
        val a = 0b00011110
        val b = 0b01111000
        assertEquals(0b01111110, a or b)
    }

    @Test
    fun `applies xor bit operator on two integers`() {
        val a = 0b00011110
        val b = 0b01111000
        assertEquals(0b01100110, a xor b)
    }

    /*
        Bit Shift Operations
     */
    @Test
    fun `shifts bits left in a integer`() {
        val a = 128
        assertEquals(512, a shl 2)
    }

    @Test
    fun `shifts bits right in a positive integer`() {
        val a = 128
        assertEquals(32, a shr 2)
    }

    @Test
    fun `shifts bits right in a negative integer`() {
        val a = -128 // 11111111111111111111111110000000
        val expected = -32 // 11111111111111111111111111100000
        assertEquals(expected, a shr 2)
    }

    @Test
    fun `shifts unsigned bits right in an integer`() {
        val a = -128 // 11111111111111111111111110000000
        val expected = 1073741792 // 00111111111111111111111111100000
        assertEquals(expected, a ushr 2)
    }

    /*
        Bitmask methods
     */
    @Test
    fun `inverts bits in a integer`() {
        val a = 0b00011110.toUByte()
        assertEquals(0b11100001.toUByte(), a.inv())
    }

    @Test
    fun `counts one bits in a integer`() {
        val a = 0b00111110.toUByte()
        assertEquals(5, a.countOneBits())
    }

    @Test
    fun `counts leading and trailing zero bits in a integer`() {
        val a = 0b00111110.toUByte()
        assertEquals(2, a.countLeadingZeroBits())
        assertEquals(1, a.countTrailingZeroBits())
    }

    @Test
    fun `takes highest and lowest one bit in a integer`() {
        val a = 0b00111110.toUByte()
        assertEquals(0b00100000, a.takeHighestOneBit().toInt())
        assertEquals(0b00000010, a.takeLowestOneBit().toInt())
    }

    @Test
    fun `rotates bits left and right in a integer`() {
        val a = 0b00111110.toUByte()
        assertEquals(0b11110001.toUByte(), a.rotateLeft(3))
        assertEquals(0b11000111.toUByte(), a.rotateRight(3))
    }
}