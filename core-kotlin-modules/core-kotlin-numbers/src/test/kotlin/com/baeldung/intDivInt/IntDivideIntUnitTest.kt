package com.baeldung.intDivInt

import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import kotlin.test.assertTrue


class IntDivideIntUnitTest {
    @Test
    fun `when using commpareTo to compare BigDecimal then get expected result `() {
        assertNotEquals(BigDecimal("0.00"), BigDecimal.ZERO)
        assertTrue { BigDecimal("0.00") compareTo BigDecimal.ZERO == 0 }
    }

    @Test
    fun `when Int 21 div Int 42 then get 0 `() {
        val result = BigDecimal(21 / 42)
        assertTrue { result compareTo BigDecimal.ZERO == 0 }
    }

    @Test
    fun `when convert an Int to Double then get the expected result `() {
        val result1 = BigDecimal(21.toDouble() / 42)
        assertTrue { result1 compareTo BigDecimal("0.5") == 0 }

        val result2 = BigDecimal(21 / 42.toDouble())
        assertTrue { result2 compareTo BigDecimal("0.5") == 0 }
    }

    @Test
    fun `when convert Int to BigDecimal then get the expected result `() {
        val result1 = 21.toBigDecimal().divide(42.toBigDecimal())
        assertTrue { result1 compareTo BigDecimal("0.5") == 0 }

        val result2 = BigDecimal.valueOf(21).divide(BigDecimal.valueOf(42))
        assertTrue { result2 compareTo BigDecimal("0.5") == 0 }

        val result3 = BigDecimal(21).divide(BigDecimal(42))
        assertTrue { result3 compareTo BigDecimal("0.5") == 0 }

    }
}