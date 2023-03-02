package com.baeldung.elvisoperator

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

class ElvisOperatorUnitTest {

    @Test
    fun `should return length of not null string without elvis operator`() {
        var word: String? = "Elvis"
        val length: Int = if (word != null) word.length else -1
        assertEquals(5, length)
    }

    @Test
    fun `should return length when string is null`() {
        val word: String? = "Elvis"
        val length: Int = word?.length ?: -1
        assertEquals(5, length)
    }

    @Test
    fun `should return -1 when string is null`() {
        val word: String? = null
        val length: Int = word?.length ?: -1
        assertEquals(-1, length)
    }

    @Test
    fun `should throw IllegalArgumentException when string is null`() {
        val word: String? = null
        val exception = assertThrows(IllegalArgumentException::class.java) {
            word?.length ?: throw IllegalArgumentException("empty string not allowed")
        }
        assertEquals("empty string not allowed", exception.message)
    }

    @Test
    fun `should return a message when a and b strings are null`() {
        val a = null
        val b = null
        val result = a ?: b ?: "a and b are null"
        assertEquals("a and b are null", result)
    }
}