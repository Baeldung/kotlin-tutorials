package com.baeldung.quicksort

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test

class QuickSortTest {

    @Test
    fun `sorts an unsorted array`() {
        val arr = intArrayOf(5, 3, 9, 7, 1)
        val expected = intArrayOf(1, 3, 5, 7, 9)
        assertArrayEquals(expected, quickSort(arr))
    }

    @Test
    fun `sorts a sorted array`() {
        val arr = intArrayOf(1, 2, 3, 4, 5)
        val expected = intArrayOf(1, 2, 3, 4, 5)
        assertArrayEquals(expected, quickSort(arr))
    }

    @Test
    fun `sorts a reverse sorted array`() {
        val arr = intArrayOf(5, 4, 3, 2, 1)
        val expected = intArrayOf(1, 2, 3, 4, 5)
        assertArrayEquals(expected, quickSort(arr))
    }

    @Test
    fun `sorts an unsorted array using lomuto's scheme`() {
        val arr = intArrayOf(5, 3, 9, 7, 1)
        quickSortLomuto(arr)
        assert(arr.contentEquals(intArrayOf(1, 3, 5, 7, 9)))
    }

    @Test
    fun `sorts a sorted array using lomuto's scheme`() {
        val arr = intArrayOf(1, 2, 3, 4, 5)
        quickSortLomuto(arr)
        assert(arr.contentEquals(intArrayOf(1, 2, 3, 4, 5)))
    }

    @Test
    fun `sorts a reverse sorted array using lomuto's scheme`() {
        val arr = intArrayOf(5, 4, 3, 2, 1)
        quickSortLomuto(arr)
        assert(arr.contentEquals(intArrayOf(1, 2, 3, 4, 5)))
    }

    @Test
    fun `sorts an unsorted array using hoare's scheme`() {
        val arr = intArrayOf(5, 3, 9, 7, 1)
        quickSortHoare(arr)
        assert(arr.contentEquals(intArrayOf(1, 3, 5, 7, 9)))
    }

    @Test
    fun `sorts a sorted array using hoare's scheme`() {
        val arr = intArrayOf(1, 2, 3, 4, 5)
        quickSortHoare(arr)
        assert(arr.contentEquals(intArrayOf(1, 2, 3, 4, 5)))
    }

    @Test
    fun `sorts a reverse sorted array using hoare's scheme`() {
        val arr = intArrayOf(5, 4, 3, 2, 1)
        quickSortHoare(arr)
        assert(arr.contentEquals(intArrayOf(1, 2, 3, 4, 5)))
    }
}
fun quickSort(arr: IntArray, left: Int = 0, right: Int = arr.size - 1): IntArray {
    var start = left
    var end = right
    val pivot = arr[(left + right) / 2]

    while (start <= end) {
        while (arr[start] < pivot) {
            start++
        }
        while (arr[end] > pivot) {
            end--
        }
        if (start <= end) {
            val temp = arr[start]
            arr[start] = arr[end]
            arr[end] = temp
            start++
            end--
        }
    }

    if (left < end) {
        quickSort(arr, left, end)
    }
    if (start < right) {
        quickSort(arr, start, right)
    }
    return arr
}

fun quickSortLomuto(arr: IntArray, start: Int = 0, end: Int = arr.size - 1) {
    if (start < end) {
        val pivot = partitionLomuto(arr, start, end)
        quickSortLomuto(arr, start, pivot - 1)
        quickSortLomuto(arr, pivot + 1, end)
    }
}

fun partitionLomuto(arr: IntArray, start: Int, end: Int): Int {
    val pivot = arr[end]
    var i = start
    for (j in start until end) {
        if (arr[j] < pivot) {
            val temp = arr[i]
            arr[i] = arr[j]
            arr[j] = temp
            i++
        }
    }
    val temp = arr[i]
    arr[i] = arr[end]
    arr[end] = temp
    return i
}

fun quickSortHoare(arr: IntArray, low: Int = 0, high: Int = arr.size - 1) {
    if (low < high) {
        val pivot = partitionHoare(arr, low, high)
        quickSortHoare(arr, low, pivot)
        quickSortHoare(arr, pivot + 1, high)
    }
}

fun partitionHoare(arr: IntArray, low: Int, high: Int): Int {
    val pivot = arr[low]
    var start = low - 1
    var end = high + 1
    while (true) {
        do {
            start++
        } while (arr[start] < pivot)
        do {
            end--
        } while (arr[end] > pivot)
        if (start >= end) {
            return end
        }
        val temp = arr[start]
        arr[start] = arr[end]
        arr[end] = temp
    }
}