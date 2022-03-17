package com.baeldung.substrings

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.*
import java.util.regex.Pattern

class SubstringsUnitTest {

    @Test
    fun `given a string when search for contained substrings then should return true`() {
        val string = "Kotlin is a programming language"
        val substring = "programming"

        val contains = string.contains(substring)
        assertThat(contains).isTrue
    }

    @Test
    fun `given a string when search for substrings in the string then should return true`() {
        val string = "Kotlin is a programming language"
        val substring = "programming"

        val contains = substring in string
        assertThat(contains).isTrue
    }

    @Test
    fun givenString_whenSearchSubstringsIgnoringCase_thenReturnsTrue() {
        val string = "Kotlin is a programming language"
        val substring = "Programming Language"

        val contains = string.contains(substring,true)
        assertThat(contains).isTrue
    }

    @Test
    fun givenString_whenSearchSubstringsUsingRegex_thenReturnsTrue() {
        val string = "Kotlin is a programming language"
        val substring = "programming language"
        val regex = substring.toRegex();

        val contains = regex.containsMatchIn(string)
        assertThat(contains).isTrue
    }

    @Test
    fun givenString_whenSearchSubstringsByIndexOf_thenReturnsNonNegative() {
        val string = "Kotlin is a programming language"
        val substring = "programming"
        val index = string.lastIndexOf(substring);

        assertThat(index).isNotNegative
    }

    @Test
    fun givenString_whenSearchSubstringsWithFindLast_thenReturnsNotNullPair() {
        val string = "Kotlin is a programming language"
        val substring = "programming language"
        val found = string.findLastAnyOf( listOf(substring));

        assertThat(found).isNotNull
    }

    @Test
    fun givenString_whenSearchSubstringsWithString_thenReturnsNotNullPair() {
        val string = "Kotlin is a programming language"
        val substring = "programming language"
        val found = string.findLastAnyOf( listOf(substring))

        assertThat(found).isNotNull
    }
}
