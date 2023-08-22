package com.baeldung.vowelOrConsonant

import org.junit.jupiter.api.Test
import kotlin.test.assertTrue
import kotlin.test.assertFalse

class VowelOrConsonantUnitTest {
    @Test
    fun `using if-else method`() {
        assertTrue(isVowel('e'))
        assertTrue(isVowel('I'))
        assertTrue(isVowel('o'))
        assertTrue(isVowel('U'))
        assertFalse(isVowel('H'))
        assertFalse(isVowel('R'))
        assertFalse(isVowel('w'))
        assertFalse(isVowel('y'))
    }

    @Test fun `using when method`() {
        assertTrue(usingWhenStatement('e'))
        assertTrue(usingWhenStatement('I'))
        assertTrue(usingWhenStatement('o'))
        assertTrue(usingWhenStatement('U'))
        assertFalse(usingWhenStatement('H'))
        assertFalse(usingWhenStatement('R'))
        assertFalse(usingWhenStatement('w'))
        assertFalse(usingWhenStatement('y'))
    }

    @Test
    fun `using Map method`() {
        assertTrue(usingMap('e'))
        assertTrue(usingMap('I'))
        assertTrue(usingMap('o'))
        assertTrue(usingMap('U'))
        assertFalse(usingMap('H'))
        assertFalse(usingMap('R'))
        assertFalse(usingMap('w'))
        assertFalse(usingMap('y'))
    }

    @Test fun `using regex method`() { assertTrue(usingRegexMethod('e'))
        assertTrue(usingRegexMethod('I'))
        assertTrue(usingRegexMethod('o'))
        assertTrue(usingRegexMethod('U'))
        assertFalse(usingRegexMethod('H'))
        assertFalse(usingRegexMethod('R'))
        assertFalse(usingRegexMethod('w'))
        assertFalse(usingRegexMethod('y'))
    }

    @Test fun `using ASCII values method`() {
        assertTrue(usingAsciiValues('e'))
        assertTrue(usingAsciiValues('I'))
        assertTrue(usingAsciiValues('o'))
        assertTrue(usingAsciiValues('U'))
        assertFalse(usingAsciiValues('H'))
        assertFalse(usingAsciiValues('R'))
        assertFalse(usingAsciiValues('w'))
        assertFalse(usingAsciiValues('y'))
    }


    fun isVowel(c: Char): Boolean {
        return when (c) {
            'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U' -> true
            else -> false
        }
    }

    fun usingWhenStatement(c: Char): Boolean {
        return when (c) {
            'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U' -> true
            else -> false
        }
    }

    fun usingMap(character: Char): Boolean {
        val vowels = mapOf(
            'a' to true,
            'e' to true,
            'i' to true,
            'o' to true,
            'u' to true )
        return vowels.containsKey(character.toLowerCase())
    }

    fun usingRegexMethod(c: Char): Boolean {
        return c.toString().matches("[AEIOUaeiou]".toRegex())
    }

    fun usingAsciiValues(c: Char): Boolean {
        val ascii = c.code
        return ascii == 65 || ascii == 69 || ascii == 73 || ascii == 79 || ascii == 85 || ascii == 97 || ascii == 101 || ascii == 105 || ascii == 111 || ascii == 117
    }
}