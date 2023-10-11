package com.baeldung.backwardIteration

import org.junit.Assert.assertEquals
import org.junit.jupiter.api.Test

class ReverseStringTest {
    @Test
    fun testReverseStringBackwards() {
        val expectedOutput = "gnirtS esreveR"
        val result = reverseStringBackwards()
        assertEquals(expectedOutput, result)
    }
    fun reverseStringBackwards(): String {
        val team = "Reverse String"
        val chars = StringBuilder(team).reverse().toString().toCharArray()
        return String(chars)
    }
}
