package com.baeldung.backwardIteration

import org.junit.Assert.assertEquals
import org.junit.jupiter.api.Test
import java.text.CharacterIterator
import java.text.StringCharacterIterator


class TraverseBackwardsTest {

    fun traverseBackwards(s: String): String {
        val it: CharacterIterator = StringCharacterIterator(s)
        var ch = it.last()
        val reversed = StringBuilder()
        while (ch != CharacterIterator.DONE) {
            reversed.append(ch)
            ch = it.previous()
        }
        return reversed.toString()
    }

    @Test
    fun testTraverseBackwards() {
        val s = "Kotlin Developer"
        val expectedOutput = "repoleveD niltoK"
        val result = traverseBackwards(s)
        assertEquals(expectedOutput, result)
    }
}
