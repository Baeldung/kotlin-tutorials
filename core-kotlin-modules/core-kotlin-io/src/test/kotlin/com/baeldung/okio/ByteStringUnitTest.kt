package com.baeldung.okio

import okio.ByteString.Companion.decodeBase64
import okio.ByteString.Companion.decodeHex
import okio.ByteString.Companion.encodeUtf8
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ByteStringUnitTest {
    @Test
    fun convertToFromUtf8() {
        val byteString = "Hello".encodeUtf8()

        assertEquals(5, byteString.size)
        assertEquals(72, byteString[0]) // H

        assertEquals("Hello", byteString.utf8())
    }

    @Test
    fun convertToFromBase64() {
        val byteString = "SGVsbG8=".decodeBase64()

        assertEquals("Hello", byteString?.utf8())

        assertEquals("SGVsbG8=", byteString?.base64())
    }

    @Test
    fun convertToFromHex() {
        val byteString = "48656c6c6f".decodeHex()

        assertEquals("Hello", byteString.utf8())

        assertEquals("48656c6c6f", byteString.hex())
    }

    @Test
    fun convertToHash() {
        val byteString = "Hello".encodeUtf8()

        assertEquals("8b1a9953c4611296a827abf8c47804d7", byteString.md5().hex())
        assertEquals("f7ff9e8b7bb2e09b70935a5d785e0cc5d9d0abf0", byteString.sha1().hex())
        assertEquals("185f8db32271fe25f561a6fc938b2e264306ec304eda518007d1764826381969", byteString.sha256().hex())
    }
}