package com.baeldung.sortalphabetically

import org.junit.Test
import kotlin.test.assertEquals

class SortStringAlphabeticallyUnitTest {

    private fun sortStringWithCharArrayAndSorted(input : String) : String{
        return input.toCharArray().sorted().joinToString("")
    }
    
    private fun sortStringWithCharArrayAnddistinct(input : String) : String {
        return input.toCharArray().sorted().distinct().joinToString("")
    }

    private fun sortStringWithtoCharArrayAndCompareBy(input: String) : String {
        val vocal = setOf('a', 'e', 'i', 'o', 'u')
        return input.toCharArray().sortedWith(compareBy<Char> { it in vocal }
                .thenBy {it.isLetter() && it !in vocal}
                .thenBy { it }
            ).joinToString("")
    }

    @Test
    fun `using toCharArray and then sorted`() {
        val inputString = "laibkcedfgjh"
        assertEquals("abcdefghijkl", sortStringWithCharArrayAndSorted(inputString))
    }

    @Test
    fun `using sorted and distinct`() {
        val inputString = "lakibkcekdghfgjhh"
        assertEquals("abcdefghijkl", sortStringWithCharArrayAnddistinct(inputString))
    }

    @Test
    fun `using compareBy`() {
        val inputString = "laibkcedfgjh"
        assertEquals("bcdfghjklaei", sortStringWithtoCharArrayAndCompareBy(inputString))
    }

    // If we use extension function
    private fun String.sortStringWithCompareBy() : String {
        val vocal = setOf('a', 'e', 'i', 'o', 'u')
        return toCharArray().sortedWith(compareBy<Char> { it in vocal }
                .thenBy {it.isLetter() && it !in vocal}
                .thenBy { it }
            ).joinToString("")
    }

    private fun String.sortAsc() = toCharArray().sorted().joinToString("")

    @Test
    fun `simplify compareBy with extension`() {
        val inputString = "laibkcedfgjh"
        assertEquals("bcdfghjklaei", inputString.sortStringWithCompareBy())
    }

    @Test
    fun `simplify toCharArray and sorted with extension`() {
        assertEquals("abcde", "cdbae".sortAsc())
    }
}