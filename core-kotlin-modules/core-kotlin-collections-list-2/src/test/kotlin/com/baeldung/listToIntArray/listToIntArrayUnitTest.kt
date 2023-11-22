package com.baeldung.listToIntArray

import org.junit.Assert.assertArrayEquals
import org.junit.jupiter.api.Test

class listToIntArrayUnitTest {

    @Test
    fun `convert integer list to integer array using for loop`() {
        val list = listOf(1, 2, 3, 4, 5)
        val expected = intArrayOf(1, 2, 3, 4, 5)
        val result = IntArray(list.size)
        for (i in list.indices) {
            result[i] = list[i]
        }

        assertArrayEquals(expected, result)
    }

    @Test
    fun `convert integer list to integer array using the intarray constructor`() {
        val list = listOf(1, 2, 3, 4, 5)
        val expected = intArrayOf(1, 2, 3, 4, 5)
        val result = IntArray(list.size) { i -> list[i] }

        assertArrayEquals(expected, result)
    }

    @Test
    fun `convert integer list to integer array using intArray() method`() {
        val list = listOf(1, 2, 3, 4, 5)
        val expected = intArrayOf(1, 2, 3, 4, 5)
        val result = list.toIntArray()

        assertArrayEquals(expected, result)
    }

    @Test
    fun `convert integer list to integer array using map() method`() {
        val list = listOf(1, 2, 3, 4, 5)
        val expected = intArrayOf(1, 2, 3, 4, 5)
        val result = list.map { it }.toIntArray()

        assertArrayEquals(expected, result)
    }

    @Test
    fun `convert integer list to integer array using stream() method`() {
        val list = listOf(1, 2, 3, 4, 5)
        val expected = intArrayOf(1, 2, 3, 4, 5)
        val result = list.stream().mapToInt { it }.toArray()

        assertArrayEquals(expected, result)
    }

    @Test
    fun `convert integer list to integer array using toTypedArray() method`() {
        val list = listOf(1, 2, 3, 4, 5)
        val expected = intArrayOf(1, 2, 3, 4, 5)
        val result = list.toTypedArray().toIntArray()

        assertArrayEquals(expected, result)
    }
}