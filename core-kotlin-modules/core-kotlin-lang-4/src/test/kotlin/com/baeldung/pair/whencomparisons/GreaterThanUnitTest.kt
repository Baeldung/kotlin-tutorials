package com.baeldung.pair.whencomparisons

import org.junit.Assert.assertEquals
import org.junit.jupiter.api.Test

class GreaterThanUnitTest {
    fun evaluateTemperature(temperature: Int): String {
        return when {
            temperature > 30 -> "Hot"
            else -> "Cold"
        }
    }
    @Test
    fun testEvaluateTemperature() {
        assertEquals("Hot", evaluateTemperature(35))
        assertEquals("Cold", evaluateTemperature(0))
    }
}

