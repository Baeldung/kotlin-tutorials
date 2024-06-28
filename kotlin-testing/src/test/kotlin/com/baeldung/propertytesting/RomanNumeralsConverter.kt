package com.baeldung.propertytesting

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import java.util.*

class RomanNumeralsConverter {

    private val romanPairs = mapOf(
        "M" to 1000,
        "CM" to 900,
        "D" to 500,
        "CD" to 400,
        "C" to 100,
        "XC" to 90,
        "L" to 50,
        "XL" to 40,
        "X" to 10,
        "IX" to 9,
        "V" to 5,
        "IV" to 4,
        "I" to 1
    )

    fun intToRoman(num: Int): String {
        if (num < 0 || num > 3999)
            throw IllegalArgumentException("the argument has to be in [0, 4_000) range.")

        var value = num
        val roman = StringBuilder()

        for (pair in romanPairs) {
            while (value >= pair.value) {
                value -= pair.value
                roman.append(pair.key)
            }
        }
        return roman.toString()
    }

    fun romanToInt(roman: String): Int {
        var result = 0
        var i = 0
        while (i < roman.length) {
            if (i + 1 < roman.length && romanPairs.containsKey(roman.substring(i, i + 2))) {
                result += romanPairs[roman.substring(i, i + 2)]!!
                i += 2
            } else {
                result += romanPairs[roman.substring(i, i + 1)]!!
                i++
            }
        }
        return result
    }



}
