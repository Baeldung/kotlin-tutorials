package com.baeldung.backwardIteration

import org.junit.Assert.assertEquals
import org.junit.jupiter.api.Test

class ReverseTest {
    fun reverse(sentence: String): String {
        if (sentence.isEmpty()) {
            return sentence
        }
        return reverse(sentence.substring(1)) + sentence[0]
    }
    @Test
    fun testReverseEmptyString() {
        val sentence = ""
        val reversed = reverse(sentence)
        assertEquals("", reversed)
    }
    @Test
    fun testReverseSingleWord() {
        val sentence = "Baeldung"
        val reversed = reverse(sentence)
        assertEquals("gnudleaB", reversed)
    }
    @Test
    fun testReverseSentence() {
        val sentence = "Baeldung Team"
        val reversed = reverse(sentence)
        assertEquals("maeT gnudleaB", reversed)
    }
    @Test
    fun testReverseWithSpaces() {
        val sentence = "  Hello World  "
        val reversed = reverse(sentence)
        assertEquals("  dlroW olleH  ", reversed)
    }
}
