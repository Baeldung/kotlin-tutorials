package com.baeldung.propertytesting

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertEquals

class JUnit5_ExampleVsPeopertyBasedUnitTest {

    val converter = RomanNumeralsConverter()

    companion object {
        @JvmStatic
        fun intsToRomanNumerals() = listOf(
            Arguments.of(3, "III"),
            Arguments.of(4, "IV"),
            Arguments.of(58, "LVIII"),
            Arguments.of(1234 , "MCCXXXIV")
        );

        @JvmStatic
        fun randomIntsInRangeInRangeZeroTo4k() = (0..<4000).shuffled().take(300)
    }

    @ParameterizedTest
    @MethodSource("intsToRomanNumerals")
    fun `should converted integer to roman numeral (example-based test)`(integer: Int, roman: String) {
        assertEquals(roman, converter.intToRoman(integer))
    }

    @ParameterizedTest
    @MethodSource("intsToRomanNumerals")
    fun `should converted roman numeral to integer (example-based test)`(integer: Int, roman: String) {
        assertEquals(integer, converter.romanToInt(roman))
    }

    @ParameterizedTest
    @MethodSource("randomIntsInRangeInRangeZeroTo4k")
    fun `should converted integer to roman numeral and back (property-based test) `(integer: Int) {
        val roman = converter.intToRoman(integer)
        assertEquals(integer, converter.romanToInt(roman))
    }

}
