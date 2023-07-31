package com.baeldung.stringconcatenation

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class StringConcatenationUnitTest {

    private val fruitsList = listOf("apple", "banana", "orange")

    @Test
    fun givenTwoStrings_concatenateWithTemplates_thenEquals() {
        val a = "Hello"
        val b = "Baeldung"
        val c = "$a $b"

        assertEquals("Hello Baeldung", c)
    }

    @Test
    fun givenTwoStrings_concatenateWithPlusOperator_thenEquals() {
        val a = "Hello"
        val b = "Baeldung"
        val c = a + " " + b

        assertEquals("Hello Baeldung", c)
    }

    @Test
    fun givenTwoStrings_concatenateWithStringBuilder_thenEquals() {
        val a = "Hello"
        val b = "Baeldung"

        val builder = StringBuilder()
        builder.append(a).append(" ").append(b)

        val c = builder.toString()

        assertEquals("Hello Baeldung", c)
    }

    @Test
    fun givenTwoStrings_concatenateWithPlusMethod_thenEquals() {
        val a = "Hello"
        val b = "Baeldung"
        val c = a.plus(" ").plus(b)

        assertEquals("Hello Baeldung", c)
    }

    @Test
    fun givenListOfStrings_concatenateWithJoinToStringMethodUsingComma_thenReturnCsv() {
        val fruitsCsv = fruitsList.joinToString(",")
        assertEquals("apple,banana,orange", fruitsCsv)
    }

    @Test
    fun givenListOfStrings_concatenateWithJoinToStringMethodUsingCommaAndTransformed_thenReturnCsv() {
        val fruitsUppercaseCsv = fruitsList.joinToString(","){it.uppercase()}
        assertEquals("APPLE,BANANA,ORANGE", fruitsUppercaseCsv)
    }

    @Test
    fun givenListOfStrings_concatenateWithJoinToStringMethodUsingCommaAndFiltered_thenReturnCsv() {
        val fruitsFilteredCsv = fruitsList.filter{it.startsWith("b") || it.startsWith("o")}
          .joinToString(",")
        assertEquals("banana,orange", fruitsFilteredCsv)
    }

    @Test
    fun givenListOfStrings_concatenateWithReduce_thenReturnCsv() {
        val fruitsCsv = fruitsList.reduce { acc, str -> "$acc,$str" }
        assertEquals("apple,banana,orange", fruitsCsv)
    }

    @Test
    fun givenListOfStrings_concatenateWithStringBuilder_thenReturnCsv() {
        val fruitsCsvBuilder = StringBuilder()

        for ((index, str) in fruitsList.withIndex()) {
            fruitsCsvBuilder.append(str)
            if (index < fruitsList.size - 1) {
                fruitsCsvBuilder.append(",")
            }
        }

        val fruitsCsv = fruitsCsvBuilder.toString()
        assertEquals("apple,banana,orange", fruitsCsv)
    }
}
