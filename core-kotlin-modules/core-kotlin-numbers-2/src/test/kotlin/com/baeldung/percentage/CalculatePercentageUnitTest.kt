package com.baeldung.percentage

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class CalculatePercentageUnitTest {
    @Test
    fun `should return accurate division result`() {
        val count = 5
        val totalCount = 10
        val expected = 50.0
        val result = (count.toDouble() / totalCount.toDouble()) * 100.0
        assertEquals(expected, result)
    }

    @Test
    fun `when dividing 10 by 20, should return 50`() {
        val result = 10.divideToPercent(20)
        assertEquals(50.0, result)
    }

    @Test
    fun `when using infix function, 10 percentOf 200 should return 20`() {
        val result = 10 percentOf 200
        assertEquals(20.0, result)
    }

    @Test fun `calculate percentage using BigDecimal for high precision`() {
        val part = BigDecimal("25")
        val whole = BigDecimal("200")
        val expectedPercentage = BigDecimal("12.50") // Expecting 12.50% as the result
        val resultPercentage = part.percentOf(whole)
        assertTrue { resultPercentage.compareTo(expectedPercentage) == 0 }
    }
}