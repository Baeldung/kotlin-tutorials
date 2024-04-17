package com.baeldung.thousandsSeparator

import org.junit.jupiter.api.Test
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.util.*
import kotlin.test.assertEquals

class ParseStringWithThousandsSeparator {

    @Test
    fun `parses string with thousands separator using replace method`(){
        val result1 = parseStringUsingReplace("1,000", Locale.US)
        val result2 = parseStringUsingReplace("25.750", Locale.GERMAN)

        assertEquals(1000, result1.toInt())
        assertEquals(25750, result2.toInt())
    }

    @Test
    fun `parses string with thousands separator using number format class`(){
        val result1 = parseStringWithSeparatorUsingNumberFormat("1,000", Locale.US)
        val result2 = parseStringWithSeparatorUsingNumberFormat("25.750", Locale.GERMAN)

        assertEquals(1000, result1.toInt())
        assertEquals(25750, result2.toInt())
    }

    @Test
    fun `parses string with thousands separator using string tokenizer`(){
        val result1 = parseStringUsingTokenizer("1,000", Locale.US)
        val result2 = parseStringUsingTokenizer("25.750", Locale.GERMAN)

        assertEquals(1000, result1.toInt())
        assertEquals(25750, result2.toInt())
    }
}

fun parseStringUsingReplace(input: String, locale: Locale): String {
    val separator = DecimalFormatSymbols.getInstance(locale).groupingSeparator
    return input.replace(Regex("[$separator]"), "")
}

fun parseStringWithSeparatorUsingNumberFormat(input: String, locale: Locale): String {
    val number = NumberFormat.getInstance(locale)
    val num = number.parse(input)
    return num.toString()
}

fun parseStringUsingTokenizer(input: String, locale: Locale): String {
    val separator = DecimalFormatSymbols.getInstance(locale).groupingSeparator
    val tokenizer = StringTokenizer(input, separator.toString())
    val builder = StringBuilder()
    while (tokenizer.hasMoreTokens()) {
        builder.append(tokenizer.nextToken())
    }
    return builder.toString()
}