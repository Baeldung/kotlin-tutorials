package com.baeldung.pair.itandthiskeyword

import org.junit.Assert.assertEquals
import org.junit.Test

class ExtensionWithThis {
    fun String.customLength(): Int {
        return this.length
    }
    val length = "Hello, Kotlin".customLength()

    @Test
    fun testCustomLength() {
        val input = "Hello, Kotlin"
        val expectedLength = 13
        val actualLength = input.customLength()
        assertEquals(expectedLength, actualLength)
    }
}