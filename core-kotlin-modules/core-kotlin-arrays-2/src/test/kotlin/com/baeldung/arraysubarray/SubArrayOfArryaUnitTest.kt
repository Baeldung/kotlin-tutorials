package com.baeldung.arraysubarray

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test

class SubArrayOfArryaUnitTest {

    @Test
    fun `using slice() method`() {
        val array = arrayOf(1, 2, 3, 4, 5, 6)
        val subArray = array.slice(2..4)
        assertArrayEquals(intArrayOf(3, 4, 5), subArray.toIntArray())
    }

    @Test
    fun `using copyOfRange() method`() {
        val array = arrayOf(1, 2, 3, 4, 5, 6)
        val subArray = array.copyOfRange(2, 5)
        assertArrayEquals(intArrayOf(3, 4, 5), subArray.toIntArray())
    }

    @Test
    fun `using subList() method`() {
        val array = arrayOf(1, 2, 3, 4, 5, 6)
        val subArray = array.toList().subList(2, 5).toTypedArray()
        assertArrayEquals(intArrayOf(3, 4, 5), subArray.toIntArray())
    }

    @Test
    fun `using filterIndexed() method`() {
        val array = arrayOf(1, 2, 3, 4, 5, 6)
        val subArray = array.filterIndexed { index, _ -> index in 2..4 }.toTypedArray()
        assertArrayEquals(intArrayOf(3, 4, 5), subArray.toIntArray())
    }

    @Test
    fun `using drop() and take() methods`() {
        val array = arrayOf(1, 2, 3, 4, 5, 6)
        val subArray = array.drop(2).take(3).toTypedArray()
        assertArrayEquals(intArrayOf(3, 4, 5), subArray.toIntArray())
    }

    @Test
    fun `using arraycopy() method`() {
        val arr = arrayOf(1, 2, 3, 4, 5, 6)
        val start = 2
        val end = 4

        val subArray = arrayOfNulls<Int>(end - start + 1)
        System.arraycopy(arr, start, subArray, 0, subArray.size)
        assertArrayEquals(arrayOf(3, 4, 5), subArray)
    }

    @Test
    fun `using map() method`() {
        val array = arrayOf(1, 2, 3, 4, 5, 6)
        val subArray = (2..4)
            .map { i: Int -> array[i] }
            .toTypedArray()
        assertArrayEquals(intArrayOf(3, 4, 5), subArray.toIntArray())
    }

    @Test
    fun `using custom approach`() {
        val array = arrayOf(1, 2, 3, 4, 5, 6)
        val subArray = customMethod(array, 2, 4)
        assertArrayEquals(intArrayOf(3, 4, 5), subArray)
    }
}

fun customMethod(array: Array<Int>, start: Int, end: Int): IntArray {
    val subArray = IntArray(end - start + 1)
    for (i in subArray.indices) {
        subArray[i] = array[start + i]
        println(subArray[i])
    }

    return subArray
}