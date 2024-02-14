package com.baeldung.arraytoset

import org.junit.Test
import kotlin.test.assertEquals

class ArrayToSetUnitTest {
    @Test
    fun `convert array to set using toSet`() {
        val arr = arrayOf(1, 5, 2, 4, 1)
        val set = arr.toSet()
        assertEquals(setOf(1, 5, 2, 4), set)
    }

    @Test
    fun `convert array to set using setOf`() {
        val arr = arrayOf(1, 5, 2, 4, 1)
        val set = setOf(*arr)
        assertEquals(setOf(1, 5, 2, 4), set)
    }

    @Test
    fun `convert array to set using HashSet`() {
        val arr = arrayOf(1, 5, 2, 4, 1)
        val set = LinkedHashSet(arr.toList())

        assertEquals(setOf(1, 5, 2, 4), set)
    }

    @Test
    fun `convert array to set using loop`() {
        val arr = arrayOf(1, 5, 2, 4, 1)
        val mutableSet = mutableSetOf<Int>()
        for (i in arr) {
            mutableSet.add(i)
        }
        assertEquals(setOf(1, 5, 2, 4), mutableSet)
    }
}