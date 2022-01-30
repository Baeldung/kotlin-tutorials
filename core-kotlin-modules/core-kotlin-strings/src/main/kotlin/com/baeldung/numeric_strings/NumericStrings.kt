package com.baeldung.numeric_strings

object NumericStrings {
    fun isNumericToX(toCheck: String): Boolean {
        return try {
            toCheck.toDouble()
            true
        } catch (numberFormatException: NumberFormatException) {
            false
        }
    }

    fun isNumericRegex(toCheck: String): Boolean {
        val regex = "-?[0-9]+(\\.[0-9]+)?".toRegex()
        return toCheck.matches(regex)
    }

    fun isNumericAllAndIsDigit(toCheck: String): Boolean {
        return toCheck.all { char -> char.isDigit() }
    }
}
