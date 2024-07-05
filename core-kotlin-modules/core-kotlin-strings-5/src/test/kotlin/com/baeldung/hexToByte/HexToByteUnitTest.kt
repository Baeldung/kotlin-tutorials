package com.baeldung.hexToByte

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test
import java.math.BigInteger

class HexToByteUnitTest {

    @Test
    fun `convert hex string to byte array using for loop`() {
        val hexString = "48656C6C6F20576F726C64"
        val expectedByteArray = byteArrayOf(72, 101, 108, 108, 111, 32, 87, 111, 114, 108, 100)
        assertArrayEquals(expectedByteArray, hexStringToByteArrayUsingForLoop(hexString))
    }

    @Test
    fun `convert hex string to byte array using BigInteger`() {
        val hexString = "48656C6C6F20576F726C64"
        val expectedByteArray = byteArrayOf(72, 101, 108, 108, 111, 32, 87, 111, 114, 108, 100)

        assertArrayEquals(expectedByteArray, hexStringToByteArrayUsingBigInteger(hexString))
    }

    @Test
    fun `convert hex string to byte array using BigInteger using standard libraries methods`() {
        val hexString = "48656C6C6F20576F726C64"
        val expectedByteArray = byteArrayOf(72, 101, 108, 108, 111, 32, 87, 111, 114, 108, 100)

        assertArrayEquals(expectedByteArray, hexStringToByteArrayUsingStandardLibraryMethods(hexString))
    }

    @OptIn(ExperimentalStdlibApi::class)
    @Test
    fun `convert hex string to byte array using hexToByteArray`() {
        val hexString = "48656C6C6F20576F726C64"
        val expectedByteArray = byteArrayOf(72, 101, 108, 108, 111, 32, 87, 111, 114, 108, 100)

        assertArrayEquals(expectedByteArray, hexString.hexToByteArray())
    }
}
fun hexStringToByteArrayUsingForLoop(hex: String): ByteArray {
    val length = hex.length
    val byteArray = ByteArray(length / 2)
    for (i in byteArray.indices) {
        val index = i * 2
        val byte = hex.substring(index, index + 2).toInt(16).toByte()
        byteArray[i] = byte
    }
    return byteArray
}
fun hexStringToByteArrayUsingBigInteger(hexString: String): ByteArray {
    val bigInteger = BigInteger(hexString, 16)
    return bigInteger.toByteArray()
}

fun hexStringToByteArrayUsingStandardLibraryMethods(hex: String): ByteArray {
    return hex.chunked(2)
        .map { it.toInt(16).toByte() }
        .toByteArray()
}