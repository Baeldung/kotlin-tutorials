package com.baeldung.mergesort

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals

class MergeSortUnitTest {
    @Test
    fun `sorts an unsorted array`() {
        val arr = intArrayOf(5, 3, 9, 7, 1)
        val expected = intArrayOf(1, 3, 5, 7, 9)
        assertArrayEquals(expected, mergeSort(arr))
    }

    @Test
    fun `sorts a sorted array`() {
        val arr = intArrayOf(1, 2, 3, 4, 5)
        val expected = intArrayOf(1, 2, 3, 4, 5)
        assertArrayEquals(expected, mergeSort(arr))
    }

    @Test
    fun `sorts a reverse sorted array`() {
        val arr = intArrayOf(5, 4, 3, 2, 1)
        val expected = intArrayOf(1, 2, 3, 4, 5)
        assertArrayEquals(expected, mergeSort(arr))
    }

    @Test
    fun testMergeSortWithLargeArray() {
        val unsortedArray = IntArray(100000) { it }.apply { shuffle() }
        mergeSortInPlace(unsortedArray)
        val expectedArray = IntArray(100000) { it }
        assertContentEquals(unsortedArray, expectedArray)
    }
}
fun mergeSort(array: IntArray, startIndex: Int = 0, endIndex: Int = array.size-1): IntArray {
    if (array.size <= 1) {
        return array
    }

    val middleIndex = (startIndex + endIndex) / 2
    val leftArray = array.sliceArray(startIndex..middleIndex)
    val rightArray = array.sliceArray(middleIndex + 1..endIndex)

    return merge(mergeSort(leftArray), mergeSort(rightArray))
}
fun merge(leftArray: IntArray, rightArray: IntArray): IntArray {
    val mergedArray = IntArray(leftArray.size + rightArray.size)
    var leftIndex = 0
    var rightIndex = 0
    var mergedIndex = 0

    while (leftIndex < leftArray.size && rightIndex < rightArray.size) {
        if (leftArray[leftIndex] <= rightArray[rightIndex]) {
            mergedArray[mergedIndex] = leftArray[leftIndex]
            leftIndex++
        } else {
            mergedArray[mergedIndex] = rightArray[rightIndex]
            rightIndex++
        }
        mergedIndex++
    }

    while (leftIndex < leftArray.size) {
        mergedArray[mergedIndex] = leftArray[leftIndex]
        leftIndex++
        mergedIndex++
    }

    while (rightIndex < rightArray.size) {
        mergedArray[mergedIndex] = rightArray[rightIndex]
        rightIndex++
        mergedIndex++
    }

    return mergedArray
}

fun mergeSortInPlace(arr: IntArray, left: Int = 0, right: Int = arr.size - 1) {
    if (left >= right) return

    var mid = (left + right) / 2

    mergeSortInPlace(arr, left, mid)
    mergeSortInPlace(arr, mid + 1, right)

    var i = left
    var j = mid + 1

    while (i <= mid && j <= right) {
        if (arr[i] <= arr[j]) {
            i++
        } else {
            val temp = arr[j]
            for (k in j downTo i + 1) {
                arr[k] = arr[k - 1]
            }
            arr[i] = temp
            i++
            mid++
            j++
        }
    }
}