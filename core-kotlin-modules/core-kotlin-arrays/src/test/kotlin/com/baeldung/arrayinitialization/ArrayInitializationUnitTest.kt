package com.baeldung.arrayinitialization

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ArrayInitializationUnitTest {

    @Test
    fun `Given an array of strings, Then values are populated`() {
        val strings = arrayOf("January", "February", "March")

        assertEquals(3, strings.size)
        assertEquals("March", strings[2])
    }

    @Test
    fun `Given an array of integers, Then values are populated`() {
        val integers = intArrayOf(1, 2, 3, 4)

        assertEquals(4, integers.size)
        assertEquals(1, integers[0])
    }

    @Test
    fun `Given an array of nulls, When populated, Then values are present`() {
        val array = arrayOfNulls<Number>(5)

        for (i in array.indices) {
            array[i] = i * i
        }

        assertEquals(16, array[4])
    }

    @Test
    fun `When using generator, Then values are present`() {
        val generatedArray = IntArray(10) { i -> i * i }

        assertEquals(81, generatedArray[9])
    }

    @Test
    fun `When generating strings, Then values are present`() {
        val generatedStringArray = Array(10) { i -> "Number of index: $i" }

        assertEquals("Number of index: 0", generatedStringArray[0])
    }

}