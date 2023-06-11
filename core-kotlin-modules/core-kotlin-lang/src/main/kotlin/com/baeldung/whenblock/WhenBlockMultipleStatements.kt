package com.baeldung.whenblock

object WhenBlockMultipleStatements {

    fun isPositiveInt(number: Int): Boolean {
        val result = when (number) {
            0 -> {
                println("number is zero.")
                print("It's neither positive nor negative.")
                return false
            }
            in -1 downTo Int.MIN_VALUE -> {
                print("number is negative")
                return false
            }
            else -> {
                print("number is positive")
                return true
            }
        }
        return result
    }
}