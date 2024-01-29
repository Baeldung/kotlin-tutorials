package com.baeldung.intArrayToStringArray

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class IntArrayToStringArrayUnitTest {

    @Test
    fun `when using toInt then it converts array`() {
        val stringArray = arrayOf("1", "2", "3", "4", "5")
        val intArray = stringArray.map { it.toInt() }.toIntArray()
        assertThat(intArray.all { it is Int }).isTrue()
        assertThat(intArray).isEqualTo(arrayOf(1,2,3,4,5))
    }

    @Test
    fun `when using toInt then exception is thrown`() {
        val stringArray = arrayOf("1", "2", "3", "four", "5")
        assertThrows<NumberFormatException> { stringArray.map { it.toInt() } }
    }

    @Test
    fun `when using toInt then exception is handled`() {
        val stringArray = arrayOf("1", "2", "3", "four", "5")
        val intList = mutableListOf<Int>()
        for (element in stringArray) {
            try {
                val intValue = element.toInt()
                intList.add(intValue)
            } catch (e: NumberFormatException) {
                // Handle the case when provided value is not a number
            }
        }
        val intArray = intList.toIntArray()
        assertThat(intArray).isEqualTo(arrayOf(1,2,3,5))
    }

    @Test
    fun `when using toIntOrNull then it converts array`() {
        val stringArray = arrayOf("1", "2", "3", "four", "5")
        val intArray = stringArray.mapNotNull { it.toIntOrNull() }.toIntArray()
        assertThat(intArray.all { it is Int }).isTrue()
        assertThat(intArray).isEqualTo(arrayOf(1,2,3,5))
    }
}