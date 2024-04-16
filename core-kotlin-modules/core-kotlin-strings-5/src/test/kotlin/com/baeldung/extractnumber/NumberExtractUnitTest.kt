package com.baeldung.extractnumber

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.math.BigInteger

fun extractUsingRegex(str: String): BigInteger? {
    return Regex("\\d+").find(str)?.value?.toBigIntegerOrNull()
}

fun extractMultipleUsingRegex(str: String): List<BigInteger> {
    return Regex("\\d+").findAll(str).map { it.value.toBigInteger() }.toList()
}

fun extractNumbersUsingSplitAndRegex(str: String): List<BigInteger> {
    return str.split(Regex("\\D+"))
        .filter { it.isNotBlank() }
        .map { it.toBigInteger() }
}

fun extractNumbersUsingLoop(str: String): List<BigInteger> {
    val numbers = mutableListOf<BigInteger>()
    val currentNumber = StringBuilder()

    for (char in str) {
        if (char.isDigit()) {
            currentNumber.append(char)
        } else if (currentNumber.isNotEmpty()) {
            numbers.add(currentNumber.toString().toBigInteger())
            currentNumber.clear()
        }
    }

    if (currentNumber.isNotEmpty()) {
        numbers.add(currentNumber.toString().toBigInteger())
    }

    return numbers
}


class NumberExtractUnitTest {

    @ParameterizedTest
    @CsvSource(
        "string with 123 and 333 in the text, 123",
        "another string with 456 and 789, 456",
        "string 123-234, 123",
        "no numbers,",
        "3 4 50 numbers6, 3",
        "123456789large numbers0, 123456789",
        "91234567891011121314151617181920number,91234567891011121314151617181920"
    )
    fun `extract first occurrence of number from string using regex`(str: String, expected: String?) {
        val number = extractUsingRegex(str)
        Assertions.assertEquals(number, expected?.toBigIntegerOrNull())
    }

    @ParameterizedTest
    @CsvSource(
        "string with 123 and 333 in the text, 123:333",
        "another string with 456 and 789, 456:789",
        "string 123-234, 123:234",
        "no numbers,",
        "3 4 50 numbers6, 3:4:50:6",
        "123456789large numbers0, 123456789:0",
        "91234567891011121314151617181920number,91234567891011121314151617181920"
    )
    fun `extract all occurrences of numbers from string using regex`(str: String, expected: String?) {
        val numbers = extractMultipleUsingRegex(str)
        val expectedList = expected?.split(":")?.map { it.toBigInteger() } ?: emptyList()
        Assertions.assertEquals(expectedList, numbers)
    }

    @ParameterizedTest
    @CsvSource(
        "string with 123 and 333 in the text, 123:333",
        "another string with 456 and 789, 456:789",
        "string 123-234, 123:234",
        "no numbers,",
        "3 4 50 numbers6, 3:4:50:6",
        "123456789large numbers0, 123456789:0",
        "91234567891011121314151617181920number,91234567891011121314151617181920"
    )
    fun `extract all occurrences of numbers from string using split and regex`(str: String, expected: String?) {
        val numbers = extractNumbersUsingSplitAndRegex(str)
        val expectedList = expected?.split(":")?.map { it.toBigInteger() } ?: emptyList()
        Assertions.assertEquals(expectedList, numbers)
    }

    @ParameterizedTest
    @CsvSource(
        "string with 123 and 333 in the text, 123:333",
        "another string with 456 and 789, 456:789",
        "string 123-234, 123:234",
        "no numbers,",
        "3 4 50 numbers6, 3:4:50:6",
        "123456789large numbers0, 123456789:0",
        "91234567891011121314151617181920number,91234567891011121314151617181920"
    )
    fun `extract all occurrences of numbers from string using loop`(str: String, expected: String?) {
        val numbers = extractNumbersUsingLoop(str)
        val expectedList = expected?.split(":")?.map { it.toBigInteger() } ?: emptyList()
        Assertions.assertEquals(expectedList, numbers)
    }

    @Test
    fun `check empty string scenario for loop`() {
        val numbers = extractNumbersUsingLoop("")
        Assertions.assertIterableEquals(numbers, listOf<BigInteger>())
    }

    @Test
    fun `check empty string scenario for split`() {
        val numbers = extractNumbersUsingSplitAndRegex("")
        Assertions.assertIterableEquals(numbers, listOf<BigInteger>())
    }

    @Test
    fun `check empty string scenario for regex`() {
        val numbers = extractMultipleUsingRegex("")
        Assertions.assertIterableEquals(numbers, listOf<BigInteger>())
    }

}