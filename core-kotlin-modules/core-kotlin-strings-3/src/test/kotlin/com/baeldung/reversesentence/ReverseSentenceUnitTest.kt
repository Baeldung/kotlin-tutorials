package com.baeldung.reversesentence
import org.junit.Assert.assertEquals
import org.junit.Test

class ReverseSentenceUnitTest {
    @Test
    fun `reverse sentence using custom method`() {
        val sentence = "this is a sentence"
        val expected = "ecnetnes a si siht"
        assertEquals(expected, reverseSentence(sentence))
    }

    @Test
    fun `reverse sentence using string reversed() method`() {
        val sentence = "this is a sentence"
        val expected = "ecnetnes a si siht"
        assertEquals(expected, sentence.reversed())
    }

    @Test
    fun `reverse sentence using stringbuilder reverse() method`() {
        val sentence = "this is a sentence"
        val expected = "ecnetnes a si siht"
        assertEquals(expected, StringBuilder(sentence).reverse().toString())
    }

    @Test
    fun `reverse sentence using recursion method`() {
        val sentence = "this is a sentence"
        val expected = "ecnetnes a si siht"
        assertEquals(expected, reverseSentenceRecursively(sentence))
    }

}
fun reverseSentence(sentence: String): String {
    var reversedSentence = ""
    for (i in sentence.length - 1 downTo 0) {
        reversedSentence += sentence[i]
    }
    return reversedSentence
}
fun reverseSentenceRecursively(sentence: String): String {
    return if (sentence.isEmpty()) {
        ""
    } else {
        reverseSentence(sentence.substring(1)) + sentence[0]
    }
}