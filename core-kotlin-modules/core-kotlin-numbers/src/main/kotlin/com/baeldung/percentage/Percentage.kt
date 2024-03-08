package com.baeldung.percentage

import java.math.BigDecimal
import java.math.RoundingMode

fun Number.divideToPercent(divideTo: Number): Double {
    if (divideTo.toDouble() == 0.0) return 0.0
    return (this.toDouble() / divideTo.toDouble()) * 100.0
}

infix fun Number.percentOf(value: Number): Double {
    return if (this.toDouble() == 0.0) 0.0
    else (value.toDouble() / this.toDouble())
}

fun BigDecimal.percentOf(total: BigDecimal): BigDecimal {
    return if (total == BigDecimal.ZERO) BigDecimal.ZERO
    else this.divide(total, 5, RoundingMode.HALF_UP) * BigDecimal(100)
}

fun Number.formatPercent() = "$this%"