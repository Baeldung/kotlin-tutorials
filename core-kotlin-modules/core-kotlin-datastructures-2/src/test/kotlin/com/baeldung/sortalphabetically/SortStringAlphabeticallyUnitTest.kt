package com.baeldung.sortalphabetically

import org.junit.Test
import kotlin.test.assertEquals

class SortStringAlphabeticallyUnitTest {


    @Test
    fun `using toCharArray and then sorted`() {
        val inputString = "laibkcedfgjh"
        val sortedString = inputString.toCharArray().sorted().joinToString("")
        println(sortedString)
        assertEquals("abcdefghijkl", sortedString)
    }

    @Test
    fun `using toCharArray and filter`() {
        val inputString = "laibkcedfgjh"
        val sortedString = inputString.toCharArray().filter { it > 'b' }.sorted().joinToString("")
        println(sortedString)
        assertEquals("cdefghijkl", sortedString)
    }

    @Test
    fun `using sorted and distinct`() {
        val inputString = "lakibkcekdghfgjhh"
        val sortedString = inputString.toCharArray().sorted().distinct().joinToString("")
        println(sortedString)
        assertEquals("abcdefghijkl", sortedString)
    }

    @Test
    fun `using compareBy`() {
        val inputString = "laibkcedfgjh"
        val vocal = setOf('a', 'e', 'i', 'o', 'u')
        val sortedString = inputString.toCharArray().sortedWith(compareBy<Char> { it in vocal }
                .thenBy {it.isLetter() && it !in vocal}
                .thenBy { it }
            ).joinToString("")
        println(sortedString)
        assertEquals("bcdfghjklaei", sortedString)
    }

    private inline fun String.sortAsc() = toCharArray().sorted().joinToString("")

    @Test
    fun `simplify with inline function`() {
        assertEquals("abcde", "cdbae".sortAsc())
    }
}