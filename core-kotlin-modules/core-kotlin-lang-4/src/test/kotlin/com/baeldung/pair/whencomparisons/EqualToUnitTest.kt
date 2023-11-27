package com.baeldung.pair.whencomparisons

import org.junit.Assert.assertEquals
import org.junit.jupiter.api.Test

class EqualToUnitTest {
    fun evaluateFruit(fruit: String): String {
        return when (fruit) {
            "apple" -> "It's an apple"
            "banana" -> "It's a banana"
            "orange" -> "It's an orange"
            else -> "It's something else"
        }
    }
    @Test
    fun testFruit() {
        assertEquals("It's an apple", evaluateFruit("apple"))
        assertEquals("It's a banana", evaluateFruit("banana"))
        assertEquals("It's an orange", evaluateFruit("orange"))
        assertEquals("It's something else", evaluateFruit("grape"))
    }
}