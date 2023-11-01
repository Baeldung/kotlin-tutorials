package com.baeldung.pair.`when`

import org.junit.Assert.assertEquals
import org.junit.jupiter.api.Test

class RangesTest {
    fun evaluateAgeCategory(age: Int): String {
        return when (age) {
            in 0..5 -> "Infant"
            in 6..12 -> "Child"
            in 13..19 -> "Teenager"
            in 20..64 -> "Adult"
            in 65..Int.MAX_VALUE -> "Senior"
            else -> "Invalid age"
        }
    }
    @Test
    fun testAgeCategories() {
        assertEquals("Infant", evaluateAgeCategory(3))
        assertEquals("Teenager", evaluateAgeCategory(14))
        assertEquals("Adult", evaluateAgeCategory(45))
    }

}