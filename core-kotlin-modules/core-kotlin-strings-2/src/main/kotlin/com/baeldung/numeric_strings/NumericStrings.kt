package com.baeldung.numeric_strings

object NumericStrings {
    object ToXTechnique {
        fun isNumeric(toCheck: String): Boolean {
            return toCheck.toDoubleOrNull() != null
        }
    }

    object RegexTechnique {
        fun isNumeric(toCheck: String): Boolean {
            val regex = "-?[0-9]+(\\.[0-9]+)?".toRegex()
            return toCheck.matches(regex)
        }
    }

    object AllAndIsDigitTechnique {
        fun isNumeric(toCheck: String): Boolean {
            return toCheck.all { char -> char.isDigit() }
        }
    }
}
