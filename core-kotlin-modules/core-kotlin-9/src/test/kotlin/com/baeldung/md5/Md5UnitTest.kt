package com.baeldung.md5

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File
import java.security.MessageDigest

class Md5UnitTest {
    @Test
    fun `Calling extension md5 should return a md5 hash`() {
        val stringToBeHashed = "Hello, Baeldung!"
        val expectedHash = "6469a4ea9e2753755f5120beb51587f8"
        val calculatedHash = stringToBeHashed.md5()

        assertEquals(expectedHash, calculatedHash)
    }

    @Test
    fun `Calling extension md5 on a file should return a md5 hash`() {
        val fileToBeHashed = File("src/test/resources/test_md5.txt")
        val expectedHash = "ef948f943cdba8514ed5aab7592a904d"
        val calculatedHash = fileToBeHashed.md5()

        assertEquals(expectedHash, calculatedHash)
    }
}

@OptIn(ExperimentalStdlibApi::class)
fun String.md5(): String {
    val md = MessageDigest.getInstance("MD5")
    val digest = md.digest(this.toByteArray())
    return digest.toHexString()
}

@OptIn(ExperimentalStdlibApi::class)
fun File.md5(): String {
    val md = MessageDigest.getInstance("MD5")
    val digest = md.digest(this.readBytes())
    return digest.toHexString()
}