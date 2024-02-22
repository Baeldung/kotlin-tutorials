package com.baeldung.settoarray

import org.junit.Test
import org.junit.jupiter.api.Assertions

class SetToArrayUnitTest {

    @Test
    fun `convert set to array using toTypedArray`() {
        val set = setOf(1, 2, 3, 4)
        val arr = set.toTypedArray()
        Assertions.assertArrayEquals(arrayOf(1, 2, 3, 4), arr)
    }

    @Test
    fun `convert set to array using Array constructor`() {
        val set = setOf(1, 2, 3, 4)
        val arr = Array(set.size) { set.elementAt(it) }
        Assertions.assertArrayEquals(arrayOf(1, 2, 3, 4), arr)
    }

    @Test
    fun `convert set to array using loop`() {
        val set = setOf(1, 2, 3, 4)
        val arr = arrayOfNulls<Int>(set.size)
        //val arr = Array(set.size) { 0 }

        var index = 0
        for (element in set) {
            arr[index++] = element
        }

        Assertions.assertArrayEquals(arrayOf(1, 2, 3, 4), arr)
    }
}