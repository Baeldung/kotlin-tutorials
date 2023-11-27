package com.baeldung.sentencereverse

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.*

class ReverseSentenceUnitTest {

    @Test
    fun `reverse sentence using programmatic approach`() {
        val sentence = "this is a complete sentence"
        assertEquals("sentence complete a is this", reverseWordsInSentenceCustomMethod(sentence))
    }

    @Test
    fun `reverse sentence using split and joinToString methods`() {
        val sentence = "this is a complete sentence"
        assertEquals("sentence complete a is this", reverseSentenceUsingSplitAndJointToStringMethod(sentence))
    }

    @Test
    fun `reverse sentence using a fold method`() {
        val sentence = "this is a complete sentence"
        assertEquals("sentence complete a is this", reverseSentenceUsingFoldMethod(sentence))
    }

    @Test
    fun `reverse sentence using a stack`() {
        val sentence = "this is a complete sentence"
        assertEquals("sentence complete a is this", reverseWordsInSentenceUsingStack(sentence))
    }

    @Test
    fun testReverseWordsInSentence() {
        assertEquals("this is a complete sentence", reverseWordsInSentence("sentence complete a is this"))
    }

    fun reverseSentenceUsingSplitAndJointToStringMethod(sentence: String): String {
        val words = sentence.split(" ")
        return words.reversed().joinToString(" ")
    }

    fun reverseWordsInSentence(sentence: String): String {
        val words = sentence.split(" ")
        return words.reduce { acc, s -> "$s $acc" }
    }

    fun reverseWordsInSentenceCustomMethod(sentence: String): String {
        val sb = StringBuilder()
        var word = ""
        for (i in sentence.length - 1 downTo 0) {
            val ch = sentence[i]
            if (ch == ' ') {
                sb.append("$word ")
                word = ""
            } else {
                word = "$ch$word"
            }
        }
        sb.append(word)
        return sb.toString()
    }

    fun reverseWordsInSentenceUsingStack(sentence: String): String {
        val stack = Stack<String>()
        val words = sentence.split(" ")
        for (word in words) {
            stack.push(word)
        }
        val reversedSentence = StringBuilder()
        while (!stack.empty()) {
            reversedSentence.append("${stack.pop()} ")
        }
        return reversedSentence.toString().trim()
    }

    fun reverseSentenceUsingFoldMethod(sentence: String): String {
        return sentence.split(" ")
            .fold("") { acc, word -> "$word $acc" }
            .trim()
    }
}