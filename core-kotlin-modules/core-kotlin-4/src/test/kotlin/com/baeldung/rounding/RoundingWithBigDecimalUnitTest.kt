package com.baeldung.rounding

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.math.RoundingMode

class RoundingWithBigDecimalUnitTest {
    private val rawPositive = 0.34444
    private val rawNegative = -0.3444

    @Test
    fun `given a positive decimal number, when formatted with a RoundingMode Up and Scale 1, round as expected`() {
        val roundedUp = rawPositive.toBigDecimal().setScale(1, RoundingMode.UP).toDouble()

        assertTrue(roundedUp == 0.4)
    }

    @Test
    fun `given a positive decimal number, when formatted with a RoundingMode Up and Scale 2, round as expected`() {
        val roundedUp = rawPositive.toBigDecimal().setScale(2, RoundingMode.UP).toDouble()

        assertTrue(roundedUp == 0.35)
    }

    @Test
    fun `given a negative decimal number, when formatted with a RoundingMode Up, round as expected`() {
        val roundedUpNegative = rawNegative.toBigDecimal().setScale(1, RoundingMode.UP).toDouble()

        assertTrue(roundedUpNegative == -0.4) // always rounds away from zero
    }

    @Test
    fun `given a positive decimal number, when formatted with a RoundingMode Ceiling, round as expected`() {
        val roundedCeiling = rawPositive.toBigDecimal().setScale(1, RoundingMode.CEILING).toDouble()

        assertTrue(roundedCeiling == 0.4)
    }

    @Test
    fun `given a negative decimal number, when formatted with a RoundingMode Ceiling, round as expected`() {
        val roundedCeilingNegative =
            rawNegative.toBigDecimal().setScale(1, RoundingMode.CEILING).toDouble()

        assertTrue(roundedCeilingNegative == -0.3) // behaves as ROUND_DOWN for negative numbers
    }

    @Test
    fun `given a positive decimal number, when formatted with a RoundingMode Down, round as expected`() {
        val roundedDown = rawPositive.toBigDecimal().setScale(1, RoundingMode.DOWN).toDouble()

        assertTrue(roundedDown == 0.3)
    }

    @Test
    fun `given a negative decimal number, when formatted with a RoundingMode Down, round as expected`() {
        val roundedDownNegative = rawNegative.toBigDecimal().setScale(1, RoundingMode.DOWN).toDouble()

        assertTrue(roundedDownNegative == -0.3) // rounds towards zero
    }

    @Test
    fun `given a positive decimal number, when formatted with a RoundingMode Floor, round as expected`() {
        val roundedFloor = rawPositive.toBigDecimal().setScale(1, RoundingMode.FLOOR).toDouble()

        assertTrue(roundedFloor == 0.3)
    }

    @Test
    fun `given a negative decimal number, when formatted with a RoundingMode Floor, round as expected`() {
        val roundedFloorNegative = rawNegative.toBigDecimal().setScale(1, RoundingMode.FLOOR).toDouble()

        assertTrue(roundedFloorNegative == -0.4) // behaves like ROUND_UP for negative numbers
    }

    @Test
    fun `given a positive decimal number, when formatted with a RoundingMode HalfUp, round as expected`() {
        val roundedHalfUp = 1.55.toBigDecimal().setScale(1, RoundingMode.HALF_UP).toDouble()

        assertTrue(roundedHalfUp == 1.6)
    }

    @Test
    fun `given a positive decimal number, when formatted with a RoundingMode HalfEven, round as expected`() {
        val roundedHalfEven = 1.55.toBigDecimal().setScale(1, RoundingMode.HALF_EVEN).toDouble()

        assertTrue(roundedHalfEven == 1.6)
    }

    @Test
    fun `given a positive decimal number, when formatted with a RoundingMode HalfDown, round as expected`() {
        val roundedHalfDown = 1.55.toBigDecimal().setScale(1, RoundingMode.HALF_DOWN).toDouble()

        assertTrue(roundedHalfDown == 1.5)
    }
}
