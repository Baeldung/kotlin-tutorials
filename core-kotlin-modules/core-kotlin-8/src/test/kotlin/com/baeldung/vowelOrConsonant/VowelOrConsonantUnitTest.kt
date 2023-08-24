package com.baeldung.vowelOrConsonant

import org.junit.jupiter.api.Test
import kotlin.test.assertTrue
import kotlin.test.assertFalse

class VowelOrConsonantUnitTest {
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
    fun `using Map method`() {
        assertTrue(isVowelUsingMap('e'))
        assertTrue(isVowelUsingMap('I'))
        assertTrue(isVowelUsingMap('o'))
        assertFalse(isConsonantUsingMap('o'))
        assertFalse(isVowelUsingMap('H'))
        assertTrue(isConsonantUsingMap('H'))
        assertFalse(isVowelUsingMap('@'))
        assertFalse(isConsonantUsingMap('@'))
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
        assertTrue(isVowelUsingAsciiValues('E'))
        assertTrue(isVowelUsingAsciiValues('I'))
        assertTrue(isVowelUsingAsciiValues('o'))
        assertFalse(isConsonantUsingAscii('o'))
        assertFalse(isVowelUsingAsciiValues('H'))
        assertTrue(isConsonantUsingAscii('H'))
        assertFalse(isVowelUsingAsciiValues('@'))
        assertFalse(isConsonantUsingAscii('@'))
    }

    fun isVowel(c: Char): Boolean {
        return when (c) {
            'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U' -> true
            else -> false
        }
    }

    fun isConsonant(c: Char): Boolean {
        return !isVowel(c) && c.isLetter()
    }

    fun isVowelUsingMap(c: Char): Boolean {
        val vowels = mapOf(
            'a' to true,
            'e' to true,
            'i' to true,
            'o' to true,
            'u' to true )
        return vowels.containsKey(c.lowercaseChar())
    }

    fun isConsonantUsingMap(c: Char): Boolean {
        return !isVowelUsingMap(c) && c.isLetter()
    }

    fun isVowelUsingRegexMethod(c: Char): Boolean {
        return c.toString().matches("[AEIOUaeiou]".toRegex())
    }

    fun isConsonantUsingRegex(c: Char): Boolean {
        val consonants = "[bcdfghjklmnpqrstvwxyz]"
        return c.lowercaseChar().toString().matches(consonants.toRegex())
    }

    fun isVowelUsingAsciiValues(c: Char): Boolean {
        val ascii = c.code
        return ascii == 65 || ascii == 69 || ascii == 73 || ascii == 79 || ascii == 85 || ascii == 97 || ascii == 101 || ascii == 105 || ascii == 111 || ascii == 117
    }

    fun isConsonantUsingAscii(c: Char): Boolean {
        val ascii = c.lowercaseChar().code
        return ascii in 97..122 && ascii !in setOf(97, 101, 105, 111, 117)
    }
}