package com.baeldung.alphabetcheck

import org.junit.jupiter.api.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class CheckAlphabetUnitTest {

    @Test
    fun `check using isLetter() method`() {
        assertTrue('a'.isLetter())
        assertTrue('w'.isLetter())
        assertTrue('T'.isLetter())
        assertTrue('P'.isLetter())
        assertFalse('%'.isLetter())
        assertFalse('4'.isLetter())
        assertFalse('.'.isLetter())
        assertFalse('*'.isLetter())

        assertTrue('\u03B2'.isLetter()) // <-- Unicode char: β
        assertFalse('\u2167'.isLetter()) // <-- Unicode Roman number: Ⅷ (Nl category)

    }

    @Test
    fun `check using range method`() {
        assertTrue(usingRangeMethod('a'))
        assertTrue(usingRangeMethod('w'))
        assertTrue(usingRangeMethod('T'))
        assertTrue(usingRangeMethod('P'))
        assertFalse(usingRangeMethod('%'))
        assertFalse(usingRangeMethod('4'))
        assertFalse(usingRangeMethod('.'))
        assertFalse(usingRangeMethod('*'))

        assertFalse(usingRangeMethod('\u03B2')) //  β
        assertFalse(usingRangeMethod('\u2167')) // Ⅷ
    }

    @Test
    fun `check using regex method`() {
        assertTrue(usingRegexMethod('a'))
        assertTrue(usingRegexMethod('w'))
        assertTrue(usingRegexMethod('T'))
        assertTrue(usingRegexMethod('P'))
        assertFalse(usingRegexMethod('%'))
        assertFalse(usingRegexMethod('4'))
        assertFalse(usingRegexMethod('.'))
        assertFalse(usingRegexMethod('*'))

        assertFalse(usingRegexMethod('\u03B2')) //  β
        assertFalse(usingRegexMethod('\u2167')) // Ⅷ
    }

    @Test
    fun `check using isAlphabetic method`() {
        assertTrue(Character.isAlphabetic('a'.code))
        assertTrue(Character.isAlphabetic('w'.code))
        assertTrue(Character.isAlphabetic('T'.code))
        assertTrue(Character.isAlphabetic('P'.code))
        assertFalse(Character.isAlphabetic('%'.code))
        assertFalse(Character.isAlphabetic('4'.code))
        assertFalse(Character.isAlphabetic('.'.code))
        assertFalse(Character.isAlphabetic('*'.code))

        assertTrue(Character.isAlphabetic('\u03B2'.code)) //  β
        assertTrue(Character.isAlphabetic('\u2167'.code)) // Ⅷ
    }

    @Test
    fun `check using ASCII method`() {
        assertTrue(usingAsciiValues('a'))
        assertTrue(usingAsciiValues('w'))
        assertTrue(usingAsciiValues('T'))
        assertTrue(usingAsciiValues('P'))
        assertFalse(usingAsciiValues('%'))
        assertFalse(usingAsciiValues('4'))
        assertFalse(usingAsciiValues('.'))
        assertFalse(usingAsciiValues('*'))

        assertFalse(usingAsciiValues('\u03B2')) //  β
        assertFalse (usingAsciiValues('\u2167')) // Ⅷ
    }

    fun usingRangeMethod(ch: Char): Boolean {
        return ch in 'A'..'Z' || ch in 'a'..'z'
    }

    fun usingRegexMethod(ch: Char): Boolean {
        return ch.toString().matches("[a-zA-Z]".toRegex())
    }

    fun usingAsciiValues(ch: Char): Boolean {
        return (ch.code in 65..90) || (ch.code in 97..122)
    }
}