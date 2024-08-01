package com.baeldung.arrayinitialization

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import com.baeldung.array.toIntArray

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

    @Test
    fun `Given a non-empty intRange is converted to List, when toIntArray invoked, Then returns intArray`() {
        val intRange = 1..10
        val intArray = intRange.toList().toIntArray();
        assertEquals(10, intArray.size)
        var index = 0
        for (element in intRange) {
            assertEquals(element, intArray[index++])
        }
    }

    @Test
    fun `Given a non-empty intRange, When toIntArray() invoked, Then returns non-empty intArray`() {
        val intRange = 1..10
        val intArray = intRange.toIntArray()
        assertEquals(10, intArray.size)
        var index = 0
        for (element in intRange) {
            assertEquals(element, intArray[index++])
        }
    }

    @Test
    fun `Given an empty intRange is converted to List, when toIntArray invoked, Then returns empty intArray`() {
        val intRange = 1..-1
        val intArray = intRange.toList().toIntArray()
        assertEquals(0, intArray.size)
    }

    @Test
    fun `Given an empty intRange, When toIntArray() invoked, Then returns an empty intArray`() {
        val intRange = 1..-1
        val intArray = intRange.toIntArray()
        assertEquals(0, intArray.size)
    }

    @Test
    fun `Given a type, When using emptyArrayOf(), then create an empty array`() {
        val emptyStringArray = emptyArray<String>()
        assertEquals(0, emptyStringArray.size)
    }

    @Test
    fun `When using intArrayOf(), then create an empty int array`() {
        val emptyIntArray = intArrayOf()
        assertEquals(0, emptyIntArray.size)
    }
}