package com.baeldung.backwardIteration

import org.junit.Assert.assertEquals
import org.junit.jupiter.api.Test

class ReverseUnitTest {
    fun reverse(sentence: String): String {
        if (sentence.isEmpty()) {
            return sentence
        }
        return reverse(sentence.substring(1)) + sentence[0]
    }
    @Test
    fun testReverseSentence() {
        val sentence = "Baeldung Team"
        val reversed = reverse(sentence)
        assertEquals("maeT gnudleaB", reversed)
    }
}

