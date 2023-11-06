package com.baeldung.listToIntArray

import org.junit.Assert.assertArrayEquals
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class listToIntArrayUnitTest {

    @Test
    fun `convert integer list to integer array using for loop`() {
        val list = listOf(1, 2, 3, 4, 5)
        val expected = intArrayOf(1, 2, 3, 4, 5)
        val array = IntArray(list.size)
        for (i in list.indices) {
            array[i] = list[i]
        }
        val actual = array

        assertArrayEquals(expected, actual)
    }

    @Test
    fun `convert integer list to integer array using for loop with index operator`() {
        val list = listOf(1, 2, 3, 4, 5)
        val expected = intArrayOf(1, 2, 3, 4, 5)
        val array = IntArray(list.size) { i -> list[i] }
        val actual = array

        assertArrayEquals(expected, actual)
    }

    @Test
    fun `convert integer list to integer array using intArray() method`() {
        val list = listOf(1, 2, 3, 4, 5)
        val expected = intArrayOf(1, 2, 3, 4, 5)
        val actual = list.toIntArray()

        assertArrayEquals(expected, actual)
    }

    @Test
    fun `convert integer list to integer array using map() method`() {
        val list = listOf(1, 2, 3, 4, 5)
        val expected = intArrayOf(1, 2, 3, 4, 5)
        val actual = list.map { it }.toIntArray()

        assertArrayEquals(expected, actual)
    }

    @Test
    fun `convert integer list to integer array using stream() method`() {
        val list = listOf(1, 2, 3, 4, 5)
        val expected = intArrayOf(1, 2, 3, 4, 5)
        val actual = list.stream().mapToInt { it }.toArray()

        assertArrayEquals(expected, actual)
    }

    @Test
    fun `convert integer list to integer array using toTypedArray() method`() {
        val list = listOf(1, 2, 3, 4, 5)
        val expected = intArrayOf(1, 2, 3, 4, 5)
        val array = list.toTypedArray().toIntArray()
        val actual = array

        assertArrayEquals(expected, actual)
    }
}