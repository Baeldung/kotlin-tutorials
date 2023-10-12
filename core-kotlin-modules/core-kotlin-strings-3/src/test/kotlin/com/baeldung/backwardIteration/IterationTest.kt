package com.baeldung.backwardIteration

import org.junit.Assert.assertEquals
import org.junit.Test

class IterationTest {
    fun iterateStringWithForLoop(text: String): String {
        val reversedText = StringBuilder()
        for (i in text.length - 1 downTo 0) {
            val char = text[i]
            reversedText.append(char)
        }
        return reversedText.toString()
    }

    @Test
    fun testIterateStringWithForLoop() {
        val originalText = "Hello, Kotlin"
        val expectedReversedText = "niltoK ,olleH"
        val result = iterateStringWithForLoop(originalText)
        assertEquals(expectedReversedText, result)
    }
}


