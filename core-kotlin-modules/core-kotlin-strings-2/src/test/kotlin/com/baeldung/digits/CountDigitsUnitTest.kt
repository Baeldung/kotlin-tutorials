package com.baeldung.digits

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CountDigitsUnitTest {

    private fun Int.countDigits() = when(this) {
        0 -> 1
        else -> kotlin.math.log10(kotlin.math.abs(toDouble())).toInt() + 1
    }

    private fun CharSequence.countDigits(): Int = when(this){
        "" -> 0
        else -> this.count { it.isDigit() }
    }
    private fun CharSequence.countDigitsCustom(): Int = when(this){
        "" -> 0
        else -> this.count { it in ("0123456789") }
    }

    private fun CharSequence.countDigitsRegex(): Int = when (this) {
        "" -> 0
        else -> this.length - this.replace(Regex("\\d"), "").length
    }

    @Test
    fun `given a value when counting digits then number of digits should be returned`() {

        val number = 12345

        assertThat(number.toString().countDigits()).isNotNull
        assertThat(number.toString().countDigits()).isEqualTo(5)
    }

    @Test
    fun `given a negative value when counting digits then number of digits should be returned`() {

        val number = -12345

        assertThat(number.toString().countDigitsCustom()).isNotNull
        assertThat(number.toString().countDigitsCustom()).isEqualTo(5)
    }

    @Test
    fun `given a double value when counting digits then number of digits should be returned`() {

        val number = 1234.509

        assertThat(number.toString().countDigitsRegex()).isNotNull
        assertThat(number.toString().countDigitsRegex()).isEqualTo(7)
    }

    @Test
    fun `given a negative double value when counting digits then number of digits should be returned`() {

        val number = -1234.509

        assertThat(number.toString().countDigitsRegex()).isNotNull
        assertThat(number.toString().countDigitsRegex()).isEqualTo(7)
    }

    @Test
    fun `given an integer value when counting digits then number of digits should be returned`() {

        val number = 123450

        assertThat(number.countDigits()).isNotNull
        assertThat(number.countDigits()).isEqualTo(6)
    }
}
