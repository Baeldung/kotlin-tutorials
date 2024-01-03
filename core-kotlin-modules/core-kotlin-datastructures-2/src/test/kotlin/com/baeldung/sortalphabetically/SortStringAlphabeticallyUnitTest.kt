package com.baeldung.sortalphabetically

import org.junit.Test
import kotlin.math.abs
import kotlin.test.assertEquals

class SortStringAlphabeticallyUnitTest {

    private val inputString = "bcade"

    @Test
    fun `using toList and sorted`() {
        val sortedString = inputString.toList().sorted().joinToString("")
        assertEquals("abcde", sortedString)
    }

    @Test
    fun `using toCharArray and sort`() {
        val charArray = inputString.toCharArray()
        charArray.sort()
        val sortedString = String(charArray)
        assertEquals("abcde", sortedString)
    }

    @Test
    fun `using toCharArray and sorted`() {
        val sortedString = inputString.toCharArray().sorted().joinToString("")
        assertEquals("abcde", sortedString)
    }

    @Test
    fun `using toList and sortedBy`() {
        val sortedString = inputString.toList().sortedBy { it }.joinToString("")
        assertEquals("abcde", sortedString)
    }

    @Test
    fun `using toList and compareBy`() {
        val sortedString = inputString.toList().sortedWith(compareBy { it }).joinToString("")
        assertEquals("abcde", sortedString)
    }

    private fun String.sortAsc() = String(toCharArray().apply { sort() })

    @Test
    fun `simplify using inline function`() {
        assertEquals("abcde", inputString.sortAsc())
    }

}