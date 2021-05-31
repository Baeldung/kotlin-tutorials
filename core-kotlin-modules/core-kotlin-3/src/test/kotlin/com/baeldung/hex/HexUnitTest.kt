package com.baeldung.hex

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.security.MessageDigest

class HexUnitTest {

    @Test
    @ExperimentalUnsignedTypes
    fun `should convert byte arrays to hex values as expected`() {
        val md5 = MessageDigest.getInstance("md5")
        md5.update("baeldung".toByteArray())

        val digest: ByteArray = md5.digest()
        // the hex value of MD5 digest for "baeldung" is "9d2ea3506b1121365e5eec24c92c528d"
        assertEquals("9d2ea3506b1121365e5eec24c92c528d", digest.toHex())
        assertEquals("9d2ea3506b1121365e5eec24c92c528d", digest.toHex2())
        assertEquals("9d2ea3506b1121365e5eec24c92c528d", digest.toHex3())
        assertEquals("9d2ea3506b1121365e5eec24c92c528d", digest.toHex4())
    }
}

fun ByteArray.toHex(): String = joinToString(separator = "") { eachByte -> "%02x".format(eachByte) }

@ExperimentalUnsignedTypes
fun ByteArray.toHex2(): String = asUByteArray().joinToString("") { it.toString(radix = 16).padStart(2, '0') }

fun ByteArray.toHex3(): String = joinToString("") {
    java.lang.Byte.toUnsignedInt(it).toString(radix = 16).padStart(2, '0')
}

val hexChars = "0123456789abcdef".toCharArray()

fun ByteArray.toHex4(): String {
    val hex = CharArray(2 * this.size)
    this.forEachIndexed { i, byte ->
        val unsigned = 0xff and byte.toInt()
        hex[2 * i] = hexChars[unsigned / 16]
        hex[2 * i + 1] = hexChars[unsigned % 16]
    }

    return hex.joinToString("")
}
