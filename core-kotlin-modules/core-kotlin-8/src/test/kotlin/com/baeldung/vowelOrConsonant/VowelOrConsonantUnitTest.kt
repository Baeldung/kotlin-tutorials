package com.baeldung.vowelOrConsonant

import org.junit.jupiter.api.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class VowelOrConsonantUnitTest {
    private val isLetterRegex = "[a-zA-Z]".toRegex()
    private val isVowelLetterRegex = "[AEIOUaeiou]".toRegex()

    @Test
    fun `using when expression`() {
        assertTrue(isVowel('e'))
        assertTrue(isVowel('I'))
        assertTrue(isVowel('o'))
        assertFalse(isConsonant('o'))
        assertFalse(isVowel('H'))
        assertTrue(isConsonant('H'))
        assertFalse(isVowel('@'))
        assertFalse(isConsonant('@'))
    }

    @Test
    fun `using Set method`() {
        assertTrue(isVowelUsingSet('e'))
        assertTrue(isVowelUsingSet('I'))
        assertTrue(isVowelUsingSet('o'))
        assertFalse(isConsonantUsingSet('o'))
        assertFalse(isVowelUsingSet('H'))
        assertTrue(isConsonantUsingSet('H'))
        assertFalse(isVowelUsingSet('@'))
        assertFalse(isConsonantUsingSet('@'))
    }

    @Test
    fun `using regex method`() {
        assertTrue(isVowelUsingRegexMethod('e'))
        assertTrue(isVowelUsingRegexMethod('I'))
        assertTrue(isVowelUsingRegexMethod('o'))
        assertFalse(isConsonantUsingRegex('o'))
        assertFalse(isVowelUsingRegexMethod('H'))
        assertTrue(isConsonantUsingRegex('H'))
        assertFalse(isVowelUsingRegexMethod('@'))
        assertFalse(isConsonantUsingRegex('@'))
    }

    @Test
    fun `using ASCII values method`() {
        assertTrue(isVowelUsingAsciiValues('E'.code))
        assertTrue(isVowelUsingAsciiValues('I'.code))
        assertTrue(isVowelUsingAsciiValues('o'.code))
        assertFalse(isConsonantUsingAscii('o'.code))
        assertFalse(isVowelUsingAsciiValues('H'.code))
        assertTrue(isConsonantUsingAscii('H'.code))
        assertFalse(isVowelUsingAsciiValues('@'.code))
        assertFalse(isConsonantUsingAscii('@'.code))
    }

    fun isVowel(c: Char): Boolean {
        return when (c.lowercaseChar()) {
            'a', 'e', 'i', 'o', 'u' -> true
            else -> false
        }
    }

    fun isConsonant(c: Char): Boolean {
        return !isVowel(c) && c.isLetter()
    }

    fun isVowelUsingSet(c: Char): Boolean {
        return c.lowercaseChar() in setOf('a', 'e', 'i', 'o', 'u')
    }

    fun isConsonantUsingSet(c: Char): Boolean {
        return !isVowelUsingSet(c) && c.isLetter()
    }

    fun isVowelUsingRegexMethod(c: Char): Boolean {
        return c.toString().matches(isVowelLetterRegex)
    }

    fun isConsonantUsingRegex(c: Char): Boolean {
        return !isVowelUsingRegexMethod(c) && c.toString().matches(isLetterRegex)
    }

    fun isVowelUsingAsciiValues(asciiCode: Int): Boolean {
        return asciiCode in setOf(65, 69, 73, 79, 85, 97, 101, 105, 111, 117)
    }

    fun isConsonantUsingAscii(asciiCode: Int): Boolean {
        return (asciiCode in 65..90 || asciiCode in 97..122) && !isVowelUsingAsciiValues(asciiCode)
    }
}