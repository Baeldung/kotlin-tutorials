package com.baeldung.rounding

import java.math.RoundingMode
import java.util.*

fun main() {
    val raw1 = 0.34
    val raw2 = 0.35
    val raw3 = 0.36

    val rounded1: Double = String.format(Locale.ENGLISH, "%.1f", raw1).toDouble()
    println("rounded1 = $rounded1") // 0.3

    val rounded2: Double = String.format("%.1f", raw2).toDouble()
    println("rounded2 = $rounded2") // 0.4

    val rounded3: Double = String.format("%.1f", raw3).toDouble()
    println("rounded3 = $rounded3") // 0.4

}
