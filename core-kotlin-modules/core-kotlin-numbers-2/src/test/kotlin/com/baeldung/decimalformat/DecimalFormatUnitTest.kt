package com.baeldung.decimalformat

import org.junit.Test
import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.util.*
import kotlin.test.assertFalse

fun formatDoubleUsingFormat(num: Double): String {
    return "%.8f".format(Locale.US, num)
}

fun formatUsingBigDecimal(num: Double): String {
    return BigDecimal.valueOf(num).toPlainString()
}

fun formatUsingString(num: Double): String {
    return String.format(Locale.US, "%.6f", num)
}

fun formatUsingNumberFormat(num: Double): String {
    val numberFormat = NumberFormat.getInstance(Locale.US)
    numberFormat.maximumFractionDigits = 8
    return numberFormat.format(num)
}

fun formatUsingDecimalFormat(num: Double): String {
    val symbols = DecimalFormatSymbols(Locale.US)
    val df = DecimalFormat("#.###############", symbols)
    return df.format(num)
}

class DecimalFormatUnitTest {

    @Test
    fun `format double using format method`() {
        val num = 0.000123456789
        println(num)
        val result = formatDoubleUsingFormat(num)
        println(result)
        assertFalse(result.contains("E"))
    }

    @Test
    fun `format double using bigdecimal method`() {
        val num: Double = 0.000123456789
        println(num)
        val result = formatUsingBigDecimal(num)
        println(result)
        assertFalse(result.contains("E"))
    }

    @Test
    fun `format double using DecimalFormat method`() {
        val num: Double = 0.000123456789
        println(num)
        val result = formatUsingDecimalFormat(num)
        println(result)
        assertFalse(result.contains("E"))
    }

    @Test
    fun `format double using String method`() {
        val num: Double = 123456789.1234567888889
        println(num)
        val result = formatUsingString(num)
        println(result)
        assertFalse(result.contains("E"))
    }

    @Test
    fun `format double using NumberFormat method`() {
        val num: Double = 0.000123456789
        println(num)
        val result = formatUsingNumberFormat(num)
        println(result)
        assertFalse(result.contains("E"))
    }
}