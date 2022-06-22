package com.baeldung.digits

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.lang.Math.abs
import java.lang.Math.log10

class CountDigitsUnitTest {

    private fun Int.countDigits() = when (this) {
        0 -> 1
        else -> log10(abs(toDouble())).toInt() + 1
    }

    private fun CharSequence.countDigits(): Int = count { it.isDigit() }

    private fun CharSequence.countDigitsCustom(): Int = when (this) {
        "" -> 0
        else -> this.count { it in ("0123456789") }
    }

    private fun CharSequence.countDigitsRegex(): Int = Regex("\\d").findAll(this).count()

    @Test
    fun `given a value when counting digits then number of digits should be returned`() {

        val number = 12345

        assertThat(number.toString().countDigits()).isNotNull().isEqualTo(5)
    }

    @Test
    fun `given a negative value when counting digits then number of digits should be returned`() {

        val number = -12345

        assertThat(number.toString().countDigitsCustom()).isNotNull().isEqualTo(5)
    }

    @Test
    fun `given a double value when counting digits then number of digits should be returned`() {

        val number = 1234.509

        assertThat(number.toString().countDigitsRegex()).isNotNull().isEqualTo(7)
    }

    @Test
    fun `given a negative double value when counting digits then number of digits should be returned`() {

        val number = -1234.509

        assertThat(number.toString().countDigitsRegex()).isNotNull().isEqualTo(7)
    }

    @Test
    fun `given an integer value when counting digits then number of digits should be returned`() {

        val number = -123450

        assertThat(number.countDigits()).isNotNull().isEqualTo(6)
    }
}
