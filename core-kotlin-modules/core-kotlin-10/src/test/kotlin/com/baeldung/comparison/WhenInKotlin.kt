package com.baeldung.comparison

fun main(number: Int): String {
    return when (number) {
        0 -> "Zero"
        1, 2 -> "One or Two"
        in 3..5 -> "Between Three and Five"
        else -> "Other"
    }
}