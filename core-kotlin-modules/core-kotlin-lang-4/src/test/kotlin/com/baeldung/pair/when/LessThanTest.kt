package com.baeldung.pair.`when`

import org.junit.Assert.assertEquals
import org.junit.jupiter.api.Test

class LessThanTest {
    fun evaluateScore(score: Int): String {
        return when {
            score < 50 -> "Fail"
            score < 75 -> "Pass"
            score < 90 -> "Good"
            else -> "Excellent"
        }
    }

    @Test
    fun testEvaluateScore() {
        assertEquals("Fail", evaluateScore(35))
        assertEquals("Pass", evaluateScore(60))
        assertEquals("Good", evaluateScore(85))
    }
}