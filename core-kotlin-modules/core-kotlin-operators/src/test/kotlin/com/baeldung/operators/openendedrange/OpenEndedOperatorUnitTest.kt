package com.baeldung.operators.openendedrange

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class OpenEndedOperatorUnitTest {

    @Test
    fun `should create use numbers between 2 (inclusive) and 5 (exclusive)`() {
        val numbers: IntRange = 2..<5
        assertEquals(numbers.toList(), listOf(2, 3, 4))
    }

    @Test
    fun `should use characters between 'a' (inclusive) and 'e' (exclusive)`() {
        val chars = ArrayList<Char>()

        for (ch in 'a'..<'e') {
            chars.add(ch)
        }

        assertEquals(chars, listOf('a', 'b', 'c', 'd'))
    }

}