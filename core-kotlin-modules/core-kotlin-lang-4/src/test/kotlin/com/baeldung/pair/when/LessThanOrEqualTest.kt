package com.baeldung.pair.`when`

import org.junit.Assert.assertEquals
import org.junit.jupiter.api.Test

class LessThanOrEqualTest {
    fun evaluateAge(age: Int): String {
        return when {
            age <= 0 -> "Invalid age"
            age <= 18 -> "Child"
            age <= 65 -> "Adult"
            else -> "Senior"
        }
    }
    @Test
    fun lessThanEqualTest() {
        assertEquals("Invalid age", evaluateAge(-5))
        assertEquals("Child", evaluateAge(12))
        assertEquals("Senior", evaluateAge(70))
    }
}

