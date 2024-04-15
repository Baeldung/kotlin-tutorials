package com.baeldung.thousandsSeparator

import org.junit.jupiter.api.Test
import java.text.NumberFormat
import java.util.*
import kotlin.test.assertEquals

class ParseStringWithThousandsSeparator {

    @Test
    fun `parses string with thousands separator using replace method`(){
        val result1 = parseStringWithCommaSeparatorUsingReplace("1,000")
        val result2 = parseStringWithDotSeparatorUsingReplace("25.750")

        assertEquals(1000, result1.toInt())
        assertEquals(25750, result2.toInt())
    }

    @Test
    fun `parses string with thousands separator using number format class`(){
        val result1 = parseStringWithSeparatorUsingNumberFormatWithUSLocale("1,000")
        val result2 = parseStringWithSeparatorUsingNumberFormatWithGermanLocale("25.750")

        assertEquals(1000, result1.toInt())
        assertEquals(25750, result2.toInt())
    }

    @Test
    fun `parses string with thousands separator using string tokenizer`(){
        val result1 = parseStringWithCommaSeparatorUsingTokenizer("1,000")
        val result2 = parseStringWithDotSeparatorUsingTokenizer("25.750")

        assertEquals(1000, result1.toInt())
        assertEquals(25750, result2.toInt())
    }
}
fun parseStringWithCommaSeparatorUsingReplace(input: String): String {
    return input.replace(Regex("[,]"), "")
}

fun parseStringWithDotSeparatorUsingReplace(input: String): String {
    return input.replace(Regex("[.]"), "")
}

fun parseStringWithSeparatorUsingNumberFormatWithUSLocale(input: String): String {
    val number = NumberFormat.getInstance(Locale.US)
    val num = number.parse(input)
    return num.toString()
}
fun parseStringWithSeparatorUsingNumberFormatWithGermanLocale(input: String): String {
    val number = NumberFormat.getInstance(Locale.GERMAN)
    val num = number.parse(input)
    return num.toString()
}
fun parseStringWithCommaSeparatorUsingTokenizer(input: String): String {
    val tokenizer = StringTokenizer(input, ",.")
    val builder = StringBuilder()
    while (tokenizer.hasMoreTokens()) {
        builder.append(tokenizer.nextToken())
    }
    return builder.toString()
}

fun parseStringWithDotSeparatorUsingTokenizer(input: String): String {
    val tokenizer = StringTokenizer(input, ".")
    val builder = StringBuilder()
    while (tokenizer.hasMoreTokens()) {
        builder.append(tokenizer.nextToken())
    }
    return builder.toString()
}