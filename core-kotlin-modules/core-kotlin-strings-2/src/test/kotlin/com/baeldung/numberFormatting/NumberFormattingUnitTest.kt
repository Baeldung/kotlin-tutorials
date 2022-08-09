package com.baeldung.numberFormatting

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

//functions
fun usingJavaStringFormat(input: Double, scale: Int) = String.format("%.${scale}f", input)

fun usingKotlinStringFormat(input: Double, scale: Int) = "%.${scale}f".format(input)

fun Double.format(scale: Int) = "%.${scale}f".format(this)

//test
class NumberFormattingUnitTest {
    companion object {
        private const val PI = 3.141592653589793
        private const val EXPECTED_SCALE_0 = "3"
        private const val EXPECTED_SCALE_2 = "3.14"
        private const val EXPECTED_SCALE_4 = "3.1416"
    }

    @Test
    fun `Given a double when calling Java String - format, should get expect format`() {
        val out0 = usingJavaStringFormat(PI, 0)
        assertThat(out0).isEqualTo(EXPECTED_SCALE_0)

        val out2 = usingJavaStringFormat(PI, 2)
        assertThat(out2).isEqualTo(EXPECTED_SCALE_2)

        val out4 = usingJavaStringFormat(PI, 4)
        assertThat(out4).isEqualTo(EXPECTED_SCALE_4)
    }

    @Test
    fun `Given a double when calling Kotlin's String - format, should get expect format`() {
        val out0 = usingKotlinStringFormat(PI, 0)
        assertThat(out0).isEqualTo(EXPECTED_SCALE_0)

        val out2 = usingKotlinStringFormat(PI, 2)
        assertThat(out2).isEqualTo(EXPECTED_SCALE_2)

        val out4 = usingKotlinStringFormat(PI, 4)
        assertThat(out4).isEqualTo(EXPECTED_SCALE_4)
    }

    @Test
    fun `Given a double when calling the extension function, should get expect format`() {
        val out0 = PI.format(0)
        assertThat(out0).isEqualTo(EXPECTED_SCALE_0)

        val out2 = PI.format(2)
        assertThat(out2).isEqualTo(EXPECTED_SCALE_2)

        val out4 = PI.format(4)
        assertThat(out4).isEqualTo(EXPECTED_SCALE_4)
    }
}