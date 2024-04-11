package com.baeldung.thousandsSeparator

import org.junit.jupiter.api.Test
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*
import kotlin.test.assertEquals

class ParseStringWithThousandsSeparator {

    @Test
    fun `parses string with thousands separator using replace method`(){
        val result1 = parseStringWithSeparatorUsingReplace("1,000")
        val result2 = parseStringWithSeparatorUsingReplace("25.750")

        assertEquals("1000", result1)
        assertEquals("25750", result2)
    }

    @Test
    fun `parses string with thousands separator using number format class`(){
        val result1 = parseStringWithSeparatorUsingNumberFormatWithUSLocale("1,000")
        val result2 = parseStringWithSeparatorUsingNumberFormatWithGermanLocale("25.750")

        assertEquals("1000", result1)
        assertEquals("25750", result2)
    }

    @Test
    fun `parses string with thousands separator using string tokenizer`(){
        val result1 = parseStringWithSeparatorUsingTokenizer("1,000")
        val result2 = parseStringWithSeparatorUsingTokenizer("25.750")

        assertEquals("1000", result1)
        assertEquals("25750", result2)
    }
}
fun parseStringWithSeparatorUsingReplace(input: String): String {
    return input.replace(Regex("[,.]"), "")
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
fun parseStringWithSeparatorUsingTokenizer(input: String): String {
    val tokenizer = StringTokenizer(input, ",.")
    val builder = StringBuilder()
    while (tokenizer.hasMoreTokens()) {
        builder.append(tokenizer.nextToken())
    }
    return builder.toString()
}