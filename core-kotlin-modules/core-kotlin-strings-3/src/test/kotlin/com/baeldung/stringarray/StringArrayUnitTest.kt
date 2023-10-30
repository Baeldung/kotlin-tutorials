package com.baeldung.stringarray

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.BufferedReader
import java.io.StringReader
import java.util.*

class StringArrayUnitTest {

    @Test
    fun `convert using split() method`(){
        val sentence = "this is a sentence"

        val expectedArr = arrayOf("this", "is", "a", "sentence")
        val actualArr = sentence.split(" ").toTypedArray()

        assertArrayEquals(expectedArr, actualArr)
    }

    @Test
    fun `convert using StringTokenizer`() {
        val sentence = "this is an example\n" +
                "of a multiline sentence\n" +
                "in Kotlin programming"

        val expectedArr = arrayOf("this", "is", "an", "example", "of", "a", "multiline", "sentence", "in", "Kotlin", "programming")

        val st = StringTokenizer(sentence)

        val actualArr = Array(st.countTokens()) { st.nextToken() }

        assertArrayEquals(expectedArr, actualArr)
    }

    @Test
    fun `convert using Regex`() {
        val sentence = "this is an example\n" +
                "of a multiline sentence\n" +
                "in Kotlin programming"

        val expectedArr = arrayOf("this", "is", "an", "example", "of", "a", "multiline", "sentence", "in", "Kotlin", "programming")
        val pattern = "\\s+".toRegex()
        val actualArr = pattern.split(sentence).toTypedArray()

        assertArrayEquals(expectedArr, actualArr)
    }

    @Test
    fun `convert using StringReader and BufferedReader`() {
        val sentence = "this is an example\n" +
                "of a multiline sentence\n" +
                "in Kotlin programming"
        val expectedArr = arrayOf("this", "is", "an", "example", "of", "a", "multiline", "sentence", "in", "Kotlin", "programming")

        val reader = BufferedReader(StringReader(sentence))

        val actualArr = reader.readLines().flatMap { it.split(" ") }.toTypedArray()
        assertArrayEquals(expectedArr, actualArr)
    }
}