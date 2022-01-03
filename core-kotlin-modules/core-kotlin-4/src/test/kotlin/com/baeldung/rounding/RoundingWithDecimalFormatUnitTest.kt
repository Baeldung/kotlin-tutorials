package com.baeldung.rounding

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

class RoundingWithDecimalFormatUnitTest {
    private val df = DecimalFormat("#.#", DecimalFormatSymbols(Locale.ENGLISH))
    private val raw1 = 0.34.toBigDecimal()
    private val raw2 = 0.35.toBigDecimal()
    private val raw3 = 0.36.toBigDecimal()

    @Test
    fun `given a decimal number, when formatted with decimal format, round down as expected`() {
        val rounded = df.format(raw1).toDouble()

        assertTrue(rounded == 0.3)
    }

    @Test
    fun `given another decimal number, when formatted with decimal format, round down as expected`() {
        val rounded = df.format(raw2).toDouble()

        assertTrue(rounded == 0.4)
    }

    @Test
    fun `given a decimal number, when formatted with decimal format, round up as expected`() {
        val rounded = df.format(raw3).toDouble()

        assertTrue(rounded == 0.4)
    }

    @Test
    fun `given a decimal number, when formatted with decimal format and a rounding mode, round down as expected`() {
        val df = DecimalFormat("#.#", DecimalFormatSymbols(Locale.ENGLISH))
        df.roundingMode = RoundingMode.FLOOR
        val rounded3Floor = df.format(raw3).toDouble()

        assertTrue(rounded3Floor == 0.3)
    }
}
