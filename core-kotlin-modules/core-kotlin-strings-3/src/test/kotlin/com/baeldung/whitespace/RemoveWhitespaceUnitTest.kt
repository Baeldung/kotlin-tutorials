package com.baeldung.whitespace

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RemoveWhitespaceUnitTest {

    @Test
    fun givenString_whenReplace_WhitespacesShouldBeRemoved() {

        val example = "House Of The Dragon"
        val withOutSpaces = example.replace(" ", "")

        assertThat(withOutSpaces).isEqualTo("HouseOfTheDragon")
    }

    @Test
    fun givenString_whenReplaceWithRegex_WhitespacesShouldBeRemoved() {

        val example = "House Of The Dragon"
        val withOutSpaces = example.replace("\\s".toRegex(), "")

        assertThat(withOutSpaces).isEqualTo("HouseOfTheDragon")
    }

    @Test
    fun givenString_whenReplaceWithRegexUnicode_WhitespacesShouldBeRemoved() {

        val example = "House Of The Dragon"
        val withOutSpaces = example.replace("\\p{Zs}+".toRegex(), "")

        assertThat(withOutSpaces).isEqualTo("HouseOfTheDragon")
    }

    @Test
    fun givenString_whenFilterByNonWhitespace_WhitespacesShouldBeRemoved() {

        val example = "House Of The Dragon"
        val withOutSpaces = example.filterNot { it.isWhitespace() }

        assertThat(withOutSpaces).isEqualTo("HouseOfTheDragon")
    }

    @Test
    fun givenString_whenTrim_ThenWhitespacesAtStartAndEndShouldBeRemoved() {

        val example = " House Of The Dragon "
        val trimmed = example.trim()

        assertThat(trimmed).isEqualTo("House Of The Dragon")
    }

    @Test
    fun givenString_whenTrimAtStartOrEnd_ThenWhitespacesAtStartOrEndShouldBeRemoved() {

        val example = "  House Of The Dragon  "
        val trimmedAtStart = example.trimStart()
        val trimmedAtEnd = example.trimEnd()

        assertThat(trimmedAtStart).isEqualTo("House Of The Dragon  ")
        assertThat(trimmedAtEnd).isEqualTo("  House Of The Dragon")
    }
}
