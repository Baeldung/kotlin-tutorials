package com.baeldung.paddingNumbers

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


class PadNumberUnitTest {
    private val inputNumbers = listOf(7, 42, 4200, 42000, 420000)
    private val expectedPadStart = listOf("00007", "00042", "04200", "42000", "420000")
    private val expectedPadEnd = listOf("7    ", "42   ", "4200 ", "42000", "420000")

    @Test
    fun `given numbers when padding start with string-format should get expected result`() {
        inputNumbers.map { "%05d".format(it) }.let {
            assertThat(it).isEqualTo(expectedPadStart)
        }
    }

    @Test
    fun `given numbers when padding end with string-format should get expected result`() {
        inputNumbers.map { "%-5d".format(it) }.let {
            assertThat(it).isEqualTo(expectedPadEnd)
        }

        inputNumbers.map { "%-5d".format(it).replace(" ", "#") }.let {
            assertThat(it).isEqualTo(listOf("7####", "42###", "4200#", "42000", "420000"))
        }

    }

    @Test
    fun `given numbers when padding with padStart should get expected result`() {
        inputNumbers.map { "$it".padStart(5, '0') }.let {
            assertThat(it).isEqualTo(expectedPadStart)
        }
    }

    @Test
    fun `given numbers when padding with padEnd should get expected result`() {
        inputNumbers.map { "$it".padEnd(5, ' ') }.let {
            assertThat(it).isEqualTo(expectedPadEnd)
        }
        inputNumbers.map { "$it".padEnd(5, '_') }.let {
            assertThat(it).isEqualTo(listOf("7____", "42___", "4200_", "42000", "420000"))
        }
    }
}