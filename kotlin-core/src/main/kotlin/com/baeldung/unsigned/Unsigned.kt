package com.baeldung.unsigned

@ExperimentalUnsignedTypes
fun main() {
    val uByte: UByte = 42u
    val uShort: UShort = 42u
    val uInt: UInt = 42U
    val uLong: ULong = 42u

    val inferredUInt = 42U
    val inferredULong = 0xFFFF_FFFF_FFFFu
    val explicitULong = 42uL

    val ba = UByteArray(42)
    val ba2 = ubyteArrayOf(42u, 43u)

    val three = 2u + 1u
    val shifted = 2u shl 3

    uintArrayOf(42u, 43u).map { it * it }.forEach { println(it) }
    val anInt = 42
    val converted = anInt.toUInt()
    println((-1).toUByte())
    println((-1).toUShort())
    println((-1).toUInt())
    println((-1L).toULong())
    println((4294967295u).toInt())

    val toUIntArray = intArrayOf(-1, -2).toUIntArray()
}