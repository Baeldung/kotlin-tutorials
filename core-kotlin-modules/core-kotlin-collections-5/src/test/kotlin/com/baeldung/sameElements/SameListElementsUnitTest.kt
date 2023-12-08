package com.baeldung.sameElements

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import kotlin.test.assertFalse

class SameListElementsUnitTest {
    @Test
    fun `test all elements are same using all method`() {
        val list1 = listOf(3, 3, 3)
        val list2 = listOf(2,3,4)
        val list3 = emptyList<Int>()
        assertTrue(areAllElementsSameUsingAllMethod(list1))
        assertFalse(areAllElementsSameUsingAllMethod(list2))
        assertTrue(areAllElementsSameUsingAllMethod(list3))
    }

    @Test
    fun `test all elements are same using for loop`() {
        val list1 = listOf(3, 3, 3)
        val list2 = listOf(2,3,4)
        val list3 = emptyList<Int>()
        assertTrue(areAllElementsSameUsingForLoop(list1))
        assertFalse(areAllElementsSameUsingForLoop(list2))
        assertTrue(areAllElementsSameUsingForLoop(list3))
    }

    @Test
    fun `test all elements are same using distinct method`() {
        val list1 = listOf(3, 3, 3)
        val list2 = listOf(2,3,4)
        val list3 = emptyList<Int>()

        assertTrue(areAllElementsSameUsingDistinctMethod(list1))
        assertFalse(areAllElementsSameUsingDistinctMethod(list2))
        assertTrue(areAllElementsSameUsingDistinctMethod(list3))
    }

    @Test
    fun `test all elements are same using set method`() {
        val list1 = listOf(3, 3, 3)
        val list2 = listOf(2,3,4)
        val list3 = emptyList<Int>()

        assertTrue(areAllElementsSameUsingSetMethod(list1))
        assertFalse(areAllElementsSameUsingSetMethod(list2))
        assertTrue(areAllElementsSameUsingSetMethod(list3))
    }

    @Test
    fun `test all elements are same using count method`() {
        val list1 = listOf(3, 3, 3)
        val list2 = listOf(2,3,4)
        val list3 = emptyList<Int>()

        assertTrue(areAllElementsSameUsingCountMethod(list1))
        assertFalse(areAllElementsSameUsingCountMethod(list2))
        assertTrue(areAllElementsSameUsingCountMethod(list3))
    }

    fun areAllElementsSameUsingAllMethod(list: List<Int>) = list.all { it == list[0] }

    fun areAllElementsSameUsingForLoop(list: List<Int>): Boolean {
        if (list.isEmpty() || list.size == 1) return true
        val firstElement = list[0]
        for (element in list) {
            if (element != firstElement) {
                return false
            }
        }
        return true
    }

    fun areAllElementsSameUsingDistinctMethod(list: List<Int>) =
         list.distinct().size <= 1

    fun areAllElementsSameUsingSetMethod(list: List<Any>): Boolean {
        return list.toSet().size <= 1
    }

    fun areAllElementsSameUsingCountMethod(list: List<Any>): Boolean {
        return list.count { it == list[0] } == list.size
    }
}