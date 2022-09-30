package com.baeldung.whitespaces

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RemoveWhitespaceTest {

    @Test
    fun givenString_whenReplace_WhitespacesShouldBeRemoved() {

        val example = "House Of The Dragon"
        val withOutSpaces = example.replace(" ", "")

        assertThat(withOutSpaces).hasToString("HouseOfTheDragon")
    }

    @Test
    fun givenString_whenReplaceWithRegex_WhitespacesShouldBeRemoved() {

        val example = "Kotlin is a programming language"
        val withOutSpaces = example.replace("\\s".toRegex(), "")

        assertThat(withOutSpaces).hasToString("Kotlinisaprogramminglanguage")
    }

    @Test
    fun givenString_whenFilterByNonWhitespace_WhitespacesShouldBeRemoved() {

        val example = "House Of The Dragon"
        val withOutSpaces = example.filter { !it.isWhitespace() }

        assertThat(withOutSpaces).hasToString("HouseOfTheDragon")
    }

    @Test
    fun givenString_whenTrim_ThenWhitespacesAtStartAndEndShouldBeRemoved() {

        val example = " House Of The Dragon "
        val trimmed = example.trim()

        assertThat(trimmed).hasToString("House Of The Dragon")
    }

    @Test
    fun givenString_whenTrimAtStartOrEnd_ThenWhitespacesAtStartOrEndShouldBeRemoved() {

        val example = "  House Of The Dragon  "
        val trimmedAtStart = example.trimStart()
        val trimmedAtEnd = example.trimEnd()

        assertThat(trimmedAtStart).hasToString("House Of The Dragon  ")
        assertThat(trimmedAtEnd).hasToString("  House Of The Dragon")
    }
}
