package com.baeldung.pair.itandthiskeyword

import org.junit.Assert.assertEquals
import org.junit.Test

class AddOneUnitTest {
    fun addOne(numbers: List<Int>): List<Int> {
        return numbers.map{ it + 1 }
    }
    @Test
    fun testAddOne() {
        val inputNumbers = listOf(1, 2, 3, 4, 5)
        val result = addOne(inputNumbers)
        val expected = listOf(2, 3, 4, 5, 6)
        assertEquals(expected, result)
    }
}