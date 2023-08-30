package com.baeldung.bubblesort

import org.junit.Assert.assertArrayEquals
import org.junit.Test

fun bubbleSort(arr: IntArray) {
    val n = arr.size
    for (i in 0 until n - 1) {
        for (j in 0 until n - i - 1) {
            if (arr[j] > arr[j + 1]) {
                val temp = arr[j]
                arr[j] = arr[j + 1]
                arr[j + 1] = temp
            }
        }
    }
}

fun bubbleSortDescending(arr: IntArray) {
    val n = arr.size
    for (i in 0 until n - 1) {
        for (j in 0 until n - i - 1) {
            if (arr[j] < arr[j + 1]) {
                val temp = arr[j]
                arr[j] = arr[j + 1]
                arr[j + 1] = temp
            }
        }
    }
}

class BubbleSort {
    @Test
    fun ascendingOrder() {
        val arr = intArrayOf(64, 34, 25, 12, 22, 11, 90)
        val expected = intArrayOf(11, 12, 22, 25, 34, 64, 90)

        bubbleSort(arr)
        assertArrayEquals(expected, arr)
    }

    @Test
    fun descendingOrder() {
        val arr = intArrayOf(64, 34, 25, 12, 22, 11, 90)
        val expected = intArrayOf(90, 64, 34, 25, 22, 12, 11)

        bubbleSortDescending(arr)
        assertArrayEquals(expected, arr)
    }
}


