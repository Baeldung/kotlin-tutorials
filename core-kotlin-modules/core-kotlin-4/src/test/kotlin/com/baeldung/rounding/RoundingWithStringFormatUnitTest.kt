package com.baeldung.rounding

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.util.*

class RoundingWithStringFormatUnitTest {
    private val raw1 = 0.34.toBigDecimal()
    private val raw2 = 0.35.toBigDecimal()
    private val raw3 = 0.36.toBigDecimal()

    @Test
    fun `given a decimal number, when formatted, round down as expected`() {
        val rounded: Double = String.format(Locale.ENGLISH, "%.1f", raw1).toDouble()

        assertTrue(rounded == 0.3)
    }

    @Test
    fun `given a decimal number, when formatted, round up as expected`() {
        val rounded: Double = String.format("%.1f", raw2).toDouble()

        assertTrue(rounded == 0.4)
    }

    @Test
    fun `given another decimal number, when formatted, round up as expected`() {
        val rounded: Double = String.format("%.1f", raw3).toDouble()

        assertTrue(rounded == 0.4)
    }
}
