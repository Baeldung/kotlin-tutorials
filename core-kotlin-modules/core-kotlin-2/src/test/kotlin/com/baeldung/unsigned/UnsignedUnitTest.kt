package com.baeldung.unsigned

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

@ExperimentalUnsignedTypes
class UnsignedUnitTest {

    val uByte: UByte = 42u
    val uShort: UShort = 42u
    val uInt: UInt = 42U
    val uLong: ULong = 42u

    val inferredUInt = 42U
    val inferredULong = 0xFFFF_FFFF_FFFFu
    val explicitULong = 42uL

    val ba = UByteArray(42)
    val ba2 = ubyteArrayOf(42u, 43u)

    val anInt = 42
    val converted = anInt.toUInt()

    val toUIntArray = intArrayOf(-1, -2).toUIntArray()

    init {
        uintArrayOf(42u, 43u).map { it * it }.forEach { println(it) }
    }

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
