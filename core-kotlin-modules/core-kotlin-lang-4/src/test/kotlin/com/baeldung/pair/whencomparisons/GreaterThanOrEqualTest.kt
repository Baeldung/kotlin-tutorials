package com.baeldung.pair.whencomparisons

import org.junit.Assert.assertEquals
import org.junit.jupiter.api.Test

class GreaterThanOrEqualTest {
    fun determineDiscount(price: Double): String {
        return when {
            price >= 100.0 -> "20% discount"
            price >= 50.0 -> "10% discount"
            else -> "No discount"
        }
    }
    @Test
    fun testDetermineDiscount() {
        assertEquals("10% discount", determineDiscount(75.0))
        assertEquals("20% discount", determineDiscount(120.0))
        assertEquals("No discount", determineDiscount(30.0))
    }
}