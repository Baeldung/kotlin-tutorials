package com.baeldung.nonalphanumeric

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class NonAlphaNumericalRemovalUnitTest {

    @Test
    fun `should be able to remove non-alphanumeric chars using replace`() {
        val text = "This notebook costs 2000€ (including tax)"
        val nonAlphaNum = "[^a-zA-Z0-9]".toRegex()
        val justAlphaNum = text.replace(nonAlphaNum, "")

        assertEquals("Thisnotebookcosts2000includingtax", justAlphaNum)
        assertEquals("hnlich", "ähnlich".replace(nonAlphaNum, ""))
        assertEquals("", "آب".replace(nonAlphaNum, ""))
        assertEquals("", "۴۲".replace(nonAlphaNum, ""))
        assertEquals("ao", "año".replace(nonAlphaNum, ""))
    }

    @Test
    fun `should be able to remove non-alphanumeric unicode chars using replace`() {
        val text = "This notebook costs 2000€ (including tax)"
        val nonAlphaNum = "[^a-zA-Z0-9\\p{L}\\p{M}*\\p{N}]".toRegex()
        val justAlphaNum = text.replace(nonAlphaNum, "")

        assertEquals("Thisnotebookcosts2000includingtax", justAlphaNum)
        assertEquals("ähnlich", "ähnlich".replace(nonAlphaNum, ""))
        assertEquals("آب", "آب".replace(nonAlphaNum, ""))
        assertEquals("۴۲", "۴۲".replace(nonAlphaNum, ""))
        assertEquals("año", "año".replace(nonAlphaNum, ""))
    }

    @Test
    fun `should be able to remove non-alphanumeric unicode chars`() {
        val text = "This notebook costs 2000€ (including tax)"
        assertEquals("Thisnotebookcosts2000includingtax", text.onlyAlphanumericChars())
        assertEquals("ähnlich", "ähnlich".onlyAlphanumericChars())
        assertEquals("آب", "آب".onlyAlphanumericChars())
        assertEquals("۴۲", "۴۲".onlyAlphanumericChars())
        assertEquals("año", "año".onlyAlphanumericChars())
    }
}

private fun String.onlyAlphanumericChars() =
    this.asSequence().filter { it.isLetterOrDigit() }.joinToString("")