package com.baeldung.array

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.nio.ByteBuffer

class AppendByteArrayUnitTest {

    val byteArray = byteArrayOf(0b00000001, 0b00000010, 0b00000011)
    val bytesToAdd = byteArrayOf(0b00000100, 0b00000101)

    @Test
    fun `Given two bytearrays, when append both with +, then return concatenated bytes`() {
        val concatenatedArray = byteArray + bytesToAdd

        Assertions.assertEquals(byteArray.size + bytesToAdd.size, concatenatedArray.size)
        Assertions.assertEquals(0b00000001, concatenatedArray[0])
        Assertions.assertEquals(0b00000010, concatenatedArray[1])
        Assertions.assertEquals(0b00000011, concatenatedArray[2])
        Assertions.assertEquals(0b00000100, concatenatedArray[3])
        Assertions.assertEquals(0b00000101, concatenatedArray[4])
    }

    @Test
    fun `Given bytearray and byte, when append both with +, then return concatenated bytes`() {
        val byteToAdd = 0b00000100.toByte()
        val concatenatedArray = byteArray + byteToAdd

        Assertions.assertEquals(byteArray.size + 1, concatenatedArray.size)
        Assertions.assertEquals(0b00000001, concatenatedArray[0])
        Assertions.assertEquals(0b00000010, concatenatedArray[1])
        Assertions.assertEquals(0b00000011, concatenatedArray[2])
        Assertions.assertEquals(0b00000100, concatenatedArray[3])
    }

    @Test
    fun `Given bytearray and int as byte, when append both with +, then return concatenated bytes`() {
        val valueToAdd = 256
        val concatenatedArray = byteArray + valueToAdd.toByte()

        Assertions.assertEquals(byteArray.size + 1, concatenatedArray.size)
        Assertions.assertEquals(0b00000001, concatenatedArray[0])
        Assertions.assertEquals(0b00000010, concatenatedArray[1])
        Assertions.assertEquals(0b00000011, concatenatedArray[2])
        Assertions.assertEquals(0b00000000, concatenatedArray[3])
    }

    @Test
    fun `Given bytearray and list of bytes, when append both with +, then return concatenated bytes`() {
        val bytesToAddList = listOf(0b00000100.toByte(), 0b00000101.toByte())
        val concatenatedArray = byteArray + bytesToAddList

        Assertions.assertEquals(0b00000001, concatenatedArray[0])
        Assertions.assertEquals(0b00000010, concatenatedArray[1])
        Assertions.assertEquals(0b00000011, concatenatedArray[2])
        Assertions.assertEquals(0b00000100, concatenatedArray[3])
        Assertions.assertEquals(0b00000101, concatenatedArray[4])
    }

    @Test
    fun `Given two bytearrays, when copy bytes using copyInto, then return all bytes`() {
        val concatenatedArray = ByteArray(byteArray.size + bytesToAdd.size)
        byteArray.copyInto(concatenatedArray)
        bytesToAdd.copyInto(concatenatedArray, destinationOffset = byteArray.size)

        Assertions.assertEquals(0b00000001, concatenatedArray[0])
        Assertions.assertEquals(0b00000010, concatenatedArray[1])
        Assertions.assertEquals(0b00000011, concatenatedArray[2])
        Assertions.assertEquals(0b00000100, concatenatedArray[3])
        Assertions.assertEquals(0b00000101, concatenatedArray[4])
    }
    @Test
    fun `Given two bytearrays, when copy with arrayCopy, then return concatenated bytes`() {
        val concatenatedArray = ByteArray(byteArray.size + bytesToAdd.size)
        System.arraycopy(byteArray, 0, concatenatedArray, 0, byteArray.size)
        System.arraycopy(bytesToAdd, 0, concatenatedArray, byteArray.size, bytesToAdd.size)

        Assertions.assertEquals(0b00000001, concatenatedArray[0])
        Assertions.assertEquals(0b00000010, concatenatedArray[1])
        Assertions.assertEquals(0b00000011, concatenatedArray[2])
        Assertions.assertEquals(0b00000100, concatenatedArray[3])
        Assertions.assertEquals(0b00000101, concatenatedArray[4])
    }

    @Test
    fun `Given two bytearrays, when write with ByteArrayOutputStream to target array, then return concatenated bytes`() {
        val outputStream = ByteArrayOutputStream()
        outputStream.write(byteArray)
        outputStream.write(bytesToAdd)

        val concatenatedArray = outputStream.toByteArray()

        Assertions.assertEquals(0b00000001, concatenatedArray[0])
        Assertions.assertEquals(0b00000010, concatenatedArray[1])
        Assertions.assertEquals(0b00000011, concatenatedArray[2])
        Assertions.assertEquals(0b00000100, concatenatedArray[3])
        Assertions.assertEquals(0b00000101, concatenatedArray[4])
    }

    @Test
    fun `Given two bytearrays, when write with ByteBuffer and get as an array, then return concatenated bytes`() {
        val buffer = ByteBuffer.allocate(byteArray.size + bytesToAdd.size)
        buffer.put(byteArray)
        buffer.put(bytesToAdd)
        val concatenatedArray = buffer.array()

        Assertions.assertEquals(0b00000001, concatenatedArray[0])
        Assertions.assertEquals(0b00000010, concatenatedArray[1])
        Assertions.assertEquals(0b00000011, concatenatedArray[2])
        Assertions.assertEquals(0b00000100, concatenatedArray[3])
        Assertions.assertEquals(0b00000101, concatenatedArray[4])
    }

    @Test
    fun `Given bytearray and int as bytearray, when append both with +, then return concatenated bytes`() {
        val valueToAdd = 256

        val buffer = ByteBuffer.allocate(byteArray.size + Integer.BYTES)
        buffer.put(byteArray)
        buffer.putInt(valueToAdd)
        val concatenatedArray = buffer.array()

        Assertions.assertEquals(byteArray.size + Integer.BYTES, concatenatedArray.size)
        Assertions.assertEquals(0b00000001, concatenatedArray[0])
        Assertions.assertEquals(0b00000010, concatenatedArray[1])
        Assertions.assertEquals(0b00000011, concatenatedArray[2])
        Assertions.assertEquals(0b00000000, concatenatedArray[3])
        Assertions.assertEquals(0b00000000, concatenatedArray[4])
        Assertions.assertEquals(0b00000001, concatenatedArray[5])
        Assertions.assertEquals(0b00000000, concatenatedArray[6])
    }
}