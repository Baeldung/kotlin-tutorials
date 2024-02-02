package com.baeldung.whenblock

object WhenBlockMultipleStatements {

    fun isPositiveInt(number: Int): Boolean {
        return when (number) {
            0 -> {
                println("number is zero.")
                print("It's neither positive nor negative.")
                false
            }

            in -1 downTo Int.MIN_VALUE -> {
                print("number is negative")
                false
            }

            else -> {
                print("number is positive")
                true
            }
        }
    }
}