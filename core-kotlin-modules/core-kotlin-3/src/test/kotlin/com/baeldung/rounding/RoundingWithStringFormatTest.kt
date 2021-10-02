package com.baeldung.rounding

import org.junit.Test
import java.util.*

class RoundingWithStringFormatTest {
    private val raw1 = 0.34
    private val raw2 = 0.35
    private val raw3 = 0.36

    @Test
    fun rounded1() {
        val rounded: Double = String.format(Locale.ENGLISH, "%.1f", raw1).toDouble()
        assert(rounded == 0.3)
    }

    @Test
    fun rounded2() {
        val rounded: Double = String.format("%.1f", raw2).toDouble()
        assert(rounded == 0.4)
    }

    @Test
    fun rounded3() {
        val rounded: Double = String.format("%.1f", raw3).toDouble()
        assert(rounded == 0.4)
    }
}
