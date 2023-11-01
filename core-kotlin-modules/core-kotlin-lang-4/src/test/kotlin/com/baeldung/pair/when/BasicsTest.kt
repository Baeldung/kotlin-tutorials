package com.baeldung.pair.`when`

import org.junit.Assert.assertEquals
import org.junit.jupiter.api.Test

class BasicsTest {

    fun describeNumber(number: Int): String {
        return when (number) {
            0 -> "It's zero"
            1 -> "It's one"
            2 -> "It's two"
            else -> "It's something else"
        }
    }
    @Test
    fun testDescribeNumber() {
        assertEquals("It's one", describeNumber(1))
        assertEquals("It's something else", describeNumber(42))
    }
}


