package com.baeldung.listReversedAndAsReversed

import org.junit.Test
import kotlin.test.assertEquals

class ReversedVsAsReversedUnitTest {
    @Test
    fun `when using reversed or asReversed then get a list with the reversed order `() {
        val inputList = listOf("item 1", "item 2", "item 3", "item 4", "item 5")
        val expected = listOf("item 5", "item 4", "item 3", "item 2", "item 1")

        val result1 = inputList.reversed()
        val result2 = inputList.asReversed()

        assertEquals(expected, result1)
        assertEquals(expected, result2)
    }

    @Test
    fun `when using reversed() then it will not reflect the changes in the original list`() {
        val inputList = mutableListOf("item 1", "item 2", "item 3", "item 4", "item 5")
        val expected = mutableListOf("item 5", "item 4", "item 3", "item 2", "item 1")

        val result = inputList.reversed()
        assertEquals(expected, result)

        // add a new element to the input list:
        inputList.add("LAST")
        assertEquals(expected, result)
    }

    @Test
    fun `when using asReversed() then it reflects the changes in the original list and vice versa`() {
        val inputList = mutableListOf("item 1", "item 2", "item 3", "item 4", "item 5")
        val expected = mutableListOf("item 5", "item 4", "item 3", "item 2", "item 1")

        val result = inputList.asReversed()
        assertEquals(expected, result)

        // add a new element to the input list:
        inputList.add("LAST")
        val expectedAfterChanges = mutableListOf("LAST", "item 5", "item 4", "item 3", "item 2", "item 1")
        assertEquals(expectedAfterChanges, result)

        // add a new element to the reversed list:
        result.add("FIRST")
        assertEquals(listOf("FIRST", "item 1", "item 2", "item 3", "item 4", "item 5", "LAST"), inputList)

    }
}