package com.baeldung.countvowelconsonant

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class CountVowelConsonantUnitTest {

    val sentence: String = "alpha beta gamma delta omega Tau Upsilon"

    @Test
    fun `test countUsingLoopAndIfCondition to vowel and consonant count should give correct results`() {
        val (vowelCount, consonantCount) = countUsingLoopAndIfCondition(sentence)
        assertEquals(16, vowelCount)
        assertEquals(18, consonantCount)
    }

    @Test
    fun `test countUsingHigherOrderFunction to vowel and consonant count should give correct results`() {
        val vowelCount: Int = countUsingHigherOrderFunction(sentence, { it in VOWELS })
        val consonantCount: Int = countUsingHigherOrderFunction(sentence, { it.isLetter() && it !in VOWELS })
        assertEquals(16, vowelCount)
        assertEquals(18, consonantCount)
    }

    @Test
    fun `test countUsingRegex to vowel and consonant count should give correct results`() {
        val (vowelCount, consonantCount) = countUsingRegex(sentence)
        assertEquals(16, vowelCount)
        assertEquals(18, consonantCount)
    }

    @Test
    fun `test countUsingLoopAndWhenExpression to vowel and consonant count should give correct results`() {
        val (vowelCount, consonantCount) = countUsingLoopAndWhenExpression(sentence)
        assertEquals(16, vowelCount)
        assertEquals(18, consonantCount)
    }

    @Test
    fun `test countUsingFold to vowel and consonant count should give correct results`() {
        val (vowelCount, consonantCount) = countUsingFold(sentence)
        assertEquals(16, vowelCount)
        assertEquals(18, consonantCount)
    }

    @Test
    fun `test countUsingFilter to vowel and consonant count should give correct results`() {
        val (vowelCount, consonantCount) = countUsingFilter(sentence)
        assertEquals(16, vowelCount)
        assertEquals(18, consonantCount)
    }

    @Test
    fun `test countUsingMap to vowel and consonant count should give correct results`() {
        val (vowelCount, consonantCount) = countUsingMap(sentence)
        assertEquals(16, vowelCount)
        assertEquals(18, consonantCount)
    }
}