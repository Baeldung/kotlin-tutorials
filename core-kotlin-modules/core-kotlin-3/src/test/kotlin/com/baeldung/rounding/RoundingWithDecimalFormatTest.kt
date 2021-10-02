package com.baeldung.rounding

import org.junit.Test
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

class RoundingWithDecimalFormatTest {
    private val df = DecimalFormat("#.#", DecimalFormatSymbols(Locale.ENGLISH))
    private val raw1 = 0.34
    private val raw2 = 0.35
    private val raw3 = 0.36

    @Test
    fun rounded1() {
        val rounded = df.format(raw1).toDouble()
        assert(rounded == 0.3)
    }

    @Test
    fun rounded2() {
        val rounded = df.format(raw2).toDouble()
        assert(rounded == 0.3)
    }

    @Test
    fun rounded3() {
        val rounded = df.format(raw3).toDouble()
        assert(rounded == 0.4)
    }

    @Test
    fun roundingMode() {
        val df = DecimalFormat("#.#", DecimalFormatSymbols(Locale.ENGLISH))
        df.roundingMode = RoundingMode.FLOOR
        val rounded3Floor = df.format(raw3).toDouble()
        assert(rounded3Floor == 0.3)
    }
}
