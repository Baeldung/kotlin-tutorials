package com.baeldung.vararg_spread

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class VarargSpread {
    /**
     * Concatenates the strings passed as vararg parameters and returns the result
     */
    private fun concat(vararg strings: String): String {
        return strings.fold("") { acc, next -> "$acc$next" }
    }

    /**
     * Concatenates the strings passed as vararg parameters and returns the result
     */
    private fun concat2(vararg strings: String, initialValue: String = "01"): String {
        return strings.fold(initialValue) { acc, next -> "$acc$next" }
    }

    /**
     * Concatenates the strings passed as vararg parameters and returns the result
     */
    private fun concat3(vararg strings: String, initialValue: String): String {
        return strings.fold(initialValue) { acc, next -> "$acc$next" }
    }

    @Test
    fun `given string array when passed to concat functions then should return concatenated string`() {
        val strings = arrayOf("ab", "cd")
        val moreStrings = arrayOf("gh", "ij")
        val listOfStrings = listOf("ab", "cd")
        assertTrue { concat(*strings) == "abcd" }
        assertTrue { concat2(*strings) == "01abcd" }
        assertTrue { concat3(strings = *strings, initialValue = "01") == "01abcd" }
        assertTrue { concat(*strings, "ef", *moreStrings) == "abcdefghij" }
        assertTrue { concat(*listOfStrings.toTypedArray()) == "abcd" }
    }
}
