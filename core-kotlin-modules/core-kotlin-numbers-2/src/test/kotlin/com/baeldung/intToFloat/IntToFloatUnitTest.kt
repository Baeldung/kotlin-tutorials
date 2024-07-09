package com.baeldung.intToFloat

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*

class IntToFloatUnitTest {

    @Test
    fun testConvertIntToFloatUsingToFloat() {
        val intValue = 12
        val floatValue = intValue.toFloat()

        assertEquals(12.0f, floatValue)
    }

    @Test
    fun testConvertIntToFloatUsingArithmetic() {
        val intValue = 12
        val floatValue = intValue + 0.0f

        assertEquals(12.0f, floatValue)
    }

    @Test
    fun testConvertIntToFloatUsingNumberFormat() {
        val intValue = 12
        val floatValue = convertIntToFloatUsingNumberFormat(intValue)
        assertEquals(12.0f, floatValue)
    }

    @Test
    fun testConvertIntToFloatUsingBigDecimal() {
        val intValue = 12
        val floatValue = BigDecimal(intValue).toFloat()

        assertEquals(12.0f, floatValue)
    }

}

fun convertIntToFloatUsingNumberFormat(value: Int): Float {
    val numberFormat = NumberFormat.getInstance(Locale.US)
    return numberFormat.parse(value.toString()).toFloat()
}