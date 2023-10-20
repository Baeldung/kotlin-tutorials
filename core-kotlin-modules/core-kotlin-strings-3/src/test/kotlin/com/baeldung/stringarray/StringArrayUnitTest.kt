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
    fun `convert using map() method`() {
        val str = "Kotlin"
        val sentence = "this is a sentence"

        val expectedArr = arrayOf("K", "o", "t", "l", "i", "n")
        val expectedArr2 = arrayOf("t", "h", "i", "s",  " ", "i", "s",  " ", "a",  " ", "s", "e", "n", "t", "e", "n", "c", "e")

        val actualArr = str.map { it.toString() }.toTypedArray()
        val actualArr2 = sentence.map { it.toString() }.toTypedArray()

        assertArrayEquals(expectedArr, actualArr)
        assertArrayEquals(expectedArr2, actualArr2)
    }

    @Test
    fun `convert using StringTokenizer`() {
        val sentence = "this is a sentence"

        val expectedArr = arrayOf("this", "is", "a", "sentence")

        val st = StringTokenizer(sentence)
        val actualArr = Array(st.countTokens()) { "" }
        var i = 0
        while (st.hasMoreTokens()) {
            actualArr[i++] = st.nextToken()
        }

        assertArrayEquals(expectedArr, actualArr)
    }

    @Test
    fun `convert using Regex`() {
        val sentence = "this is a sentence"
        val expectedArr = arrayOf("this", "is", "a", "sentence")
        val pattern = "\\s+".toRegex()
        val actualArr = sentence.split(pattern).toTypedArray()

        assertArrayEquals(expectedArr, actualArr)
    }

    @Test
    fun `convert using StringReader and BufferedReader`() {
        val sentence = "this is a sentence"
        val expectedArr = arrayOf("this", "is", "a", "sentence")

        val reader = BufferedReader(StringReader(sentence))
        val actualArr = mutableListOf<String>()
        var line: String?

        while (reader.readLine().also { line = it } != null) {
            actualArr.addAll(line!!.split(" "))
        }
        assertArrayEquals(expectedArr, actualArr.toTypedArray())
    }
}