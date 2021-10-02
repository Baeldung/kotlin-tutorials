package com.baeldung.rounding

import org.junit.Test
import java.math.RoundingMode

class RoundingWithBigDecimalTest {
    private val rawPositive = 0.34444
    private val rawNegative = -0.3444

    @Test
    fun roundedUp() {
        val roundedUp = rawPositive.toBigDecimal().setScale(1, RoundingMode.UP).toDouble()
        assert(roundedUp == 0.4)
    }

    @Test
    fun roundedUpNegative() {
        val roundedUpNegative = rawNegative.toBigDecimal().setScale(1, RoundingMode.UP).toDouble()
        assert(roundedUpNegative == -0.4) // always rounds away from zero
    }

    @Test
    fun roundedCeiling() {
        val roundedCeiling = rawPositive.toBigDecimal().setScale(1, RoundingMode.CEILING).toDouble()
        assert(roundedCeiling == 0.4)
    }

    @Test
    fun roundedCeilingNegative() {
        val roundedCeilingNegative =
            rawNegative.toBigDecimal().setScale(1, RoundingMode.CEILING).toDouble()
        assert(roundedCeilingNegative == -0.3) // behaves as ROUND_DOWN for negative numbers
    }

    @Test
    fun roundedDown() {
        val roundedDown = rawPositive.toBigDecimal().setScale(1, RoundingMode.DOWN).toDouble()
        assert(roundedDown == 0.3)
    }

    @Test
    fun roundedDownNegative() {
        val roundedDownNegative = rawNegative.toBigDecimal().setScale(1, RoundingMode.DOWN).toDouble()
        assert(roundedDownNegative == -0.3) // rounds towards zero
    }

    @Test
    fun roundedFloor() {
        val roundedFloor = rawPositive.toBigDecimal().setScale(1, RoundingMode.FLOOR).toDouble()
        assert(roundedFloor == 0.3)
    }

    @Test
    fun roundedFloorNegative() {
        val roundedFloorNegative = rawNegative.toBigDecimal().setScale(1, RoundingMode.FLOOR).toDouble()
        assert(roundedFloorNegative == -0.4) // behaves like ROUND_UP for negative numbers
    }

    @Test
    fun roundedHalfUp() {
        val roundedHalfUp = 1.55.toBigDecimal().setScale(1, RoundingMode.HALF_UP).toDouble()
        assert(roundedHalfUp == 1.6)
    }

    @Test
    fun roundedHalfEven() {
        val roundedHalfEven = 1.55.toBigDecimal().setScale(1, RoundingMode.HALF_EVEN).toDouble()
        assert(roundedHalfEven == 1.6)
    }

    @Test
    fun roundedHalfDown() {
        val roundedHalfDown = 1.55.toBigDecimal().setScale(1, RoundingMode.HALF_DOWN).toDouble()
        assert(roundedHalfDown == 1.5)
    }
}
