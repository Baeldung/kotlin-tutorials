package com.baeldung.cyclicarrayrotation

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test
import java.util.*

class CyclicArrayRotationUnitTest {
    @Test
    fun `cyclically rotate an array by one position using programmatic approach`() {
        val arr = intArrayOf(1, 2, 3, 4, 5)
        rotateArrayByOneProgramaticApproach(arr)
        assertArrayEquals(intArrayOf(5, 1, 2, 3, 4), arr)
    }

    @Test
    fun `cyclically rotate an array by one position using copyInto method`() {
        val arr = intArrayOf(1, 2, 3, 4, 5)
        rotateArrayByOneUsingCopyIntoMethod(arr)
        assertArrayEquals(intArrayOf(5, 1, 2, 3, 4), arr)
    }

    @Test
    fun `cyclically rotate an array by one position using dropLast and takeLast methods`() {
        val arr = intArrayOf(1, 2, 3, 4, 5)
        val result = rotateArrayByOneUsingdropLastAndTakeLastMethods(arr)
        assertArrayEquals(intArrayOf(5, 1, 2, 3, 4), result.toIntArray())
    }

    @Test
    fun `cyclically rotate an array by one position using Collections rotate methods`() {
        val arr = intArrayOf(1, 2, 3, 4, 5)
        rotateArrayByOneUsingCollectionsRotateMethod(arr)
        assertArrayEquals(intArrayOf(5, 1, 2, 3, 4), arr)
    }

    @Test
    fun `cyclically rotate an array by one position using recursion`() {
        val arr = intArrayOf(1, 2, 3, 4, 5)
        rotateArrayByOneUsingRecursion(arr, 0)
        assertArrayEquals(intArrayOf(5, 1, 2, 3, 4), arr)
    }

    fun rotateArrayByOneProgramaticApproach(arr: IntArray) {
        val temp = arr[arr.size - 1]
        for (i in arr.size - 1 downTo 1) {
            arr[i] = arr[i - 1]
        }
        arr[0] = temp
    }

    fun rotateArrayByOneUsingCopyIntoMethod(arr: IntArray) {
        val newArr = IntArray(arr.size)
        arr.copyInto(newArr, 1, 0, arr.size - 1)
        newArr.set(0, arr[arr.size - 1])
        newArr.copyInto(arr)
    }

    fun rotateArrayByOneUsingdropLastAndTakeLastMethods(arr: IntArray): Array<Int> {
        return (arr.takeLast(1) + arr.dropLast(1)).toTypedArray()
    }

    fun rotateArrayByOneUsingCollectionsRotateMethod(arr: IntArray) {
        val list = arr.toList().toMutableList()
        Collections.rotate(list, 1)
        list.toIntArray().copyInto(arr)
    }

    fun rotateArrayByOneUsingRecursion(arr: IntArray, position: Int) {
        if (position == arr.size - 1) return
        val temp = arr[position]
        arr[position] = arr[arr.size - 1]
        arr[arr.size - 1] = temp
        rotateArrayByOneUsingRecursion(arr, position + 1)
    }
}