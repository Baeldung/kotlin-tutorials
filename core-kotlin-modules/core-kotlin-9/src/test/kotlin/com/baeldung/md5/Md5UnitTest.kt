package com.baeldung.md5

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.security.MessageDigest

class Md5UnitTest {
    @Test
    fun `Calling extension md5 should return a md5 hash`() {
        val stringToBeHashed = "Hello, Baeldung!"
        val expectedHash = "6469a4ea9e2753755f5120beb51587f8"
        val calculatedHash = stringToBeHashed.md5()

        assertEquals(expectedHash, calculatedHash)
    }
}

fun String.md5(): String {
    val md = MessageDigest.getInstance("MD5")
    val digest = md.digest(this.toByteArray())
    return digest.joinToString("") { "%02x".format(it) }
}