package com.baeldung.rounding

import java.math.RoundingMode

fun main() {
    val rawPositive = 0.34444
    val rawNegative = -0.3444

    val roundedUp = rawPositive.toBigDecimal().setScale(1, RoundingMode.UP).toDouble()
    println("roundedUp = $roundedUp") // 0.4

    val roundedUpNegative = rawNegative.toBigDecimal().setScale(1, RoundingMode.UP).toDouble()
    println("roundedUpNegative = $roundedUpNegative") // -0.4 (always rounds away from zero)

    val roundedCeiling = rawPositive.toBigDecimal().setScale(1, RoundingMode.CEILING).toDouble()
    println("roundedCeiling = $roundedCeiling") // 0.4

    val roundedCeilingNegative =
        rawNegative.toBigDecimal().setScale(1, RoundingMode.CEILING).toDouble()
    println("roundedCeilingNegative = $roundedCeilingNegative") // -0.3 (behaves as ROUND_DOWN for negative numbers)

    val roundedDown = rawPositive.toBigDecimal().setScale(1, RoundingMode.DOWN).toDouble()
    println("roundedDown = $roundedDown") // 0.3

    val roundedDownNegative = rawNegative.toBigDecimal().setScale(1, RoundingMode.DOWN).toDouble()
    println("roundedDownNegative = $roundedDownNegative") // -0.3 (rounds towards zero)

    val roundedFloor = rawPositive.toBigDecimal().setScale(1, RoundingMode.FLOOR).toDouble()
    println("roundedFloor = $roundedFloor") // 0.3

    val roundedFloorNegative = rawNegative.toBigDecimal().setScale(1, RoundingMode.FLOOR).toDouble()
    println("roundedFloorNegative = $roundedFloorNegative") // -0.4 (behaves like ROUND_UP for negative numbers)

    val roundedHalfUp = 1.55.toBigDecimal().setScale(1, RoundingMode.HALF_UP).toDouble()
    println("roundedHalfUp = $roundedHalfUp") // 1.6

    val roundedHalfEven = 1.55.toBigDecimal().setScale(1, RoundingMode.HALF_EVEN).toDouble()
    println("roundedHalfEven = $roundedHalfEven") // 1.6

    val roundedHalfDown = 1.55.toBigDecimal().setScale(1, RoundingMode.HALF_DOWN).toDouble()
    println("roundedHalfDown = $roundedHalfDown") // 0.5
}
