package com.baeldung.equalsIgnoreCaseInKotlin

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class EqualsIgnoreCaseInKotlinUnitTest {

    @Test
    fun `test string case insensitive comparison using equals and lowercase methods`() {
         val result1 = "Hello".lowercase() == "hello".lowercase()
         val result2 = "Hello".lowercase() == "world".lowercase()

        assertTrue(result1)
        assertFalse(result2)
    }

    @Test
    fun `test string case insensitive comparison using equals methods`() {
        val result1 = "Hello".equals("hello", true)
        val result2 = "Hello".equals("world", true)

        assertTrue(result1)
        assertFalse(result2)
    }

    @Test
    fun `test string case insensitive comparison using compareTo method`() {
        val result1 = "Hello".compareTo("hello", true) == 0
        val result2 = "Hello".compareTo("world", true) == 0

        assertTrue(result1)
        assertFalse(result2)
    }
}