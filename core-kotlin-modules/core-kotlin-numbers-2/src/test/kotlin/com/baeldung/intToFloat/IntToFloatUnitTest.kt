package com.baeldung.intToFloat

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*

class IntToFloatUnitTest {

    @Test
    fun `convert int to float using toFloat() method`() {
        val intValue = 12
        val floatValue = intValue.toFloat()

        assertEquals(12.0f, floatValue)
    }

    @Test
    fun `convert int to float using arithmetic`() {
        val intValue = 12
        val floatValue = intValue + 0.0f

        assertEquals(12.0f, floatValue)
    }

    @Test
    fun `convert int to float using NumberFormat class`() {
        val intValue = 12
        val floatValue = convertIntToFloatUsingNumberFormat(intValue)
        assertEquals(12.0f, floatValue)
    }

    @Test
    fun `convert int to float using BigDecimal`() {
        val intValue = 12
        val floatValue = BigDecimal(intValue).toFloat()

        assertEquals(12.0f, floatValue)
    }

}

fun convertIntToFloatUsingNumberFormat(value: Int): Float {
    val numberFormat = NumberFormat.getInstance(Locale.US)
    return numberFormat.parse(value.toString()).toFloat()
}