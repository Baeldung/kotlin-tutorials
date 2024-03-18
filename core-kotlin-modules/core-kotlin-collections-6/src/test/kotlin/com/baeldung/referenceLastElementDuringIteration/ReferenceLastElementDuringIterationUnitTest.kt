package com.baeldung.referenceLastElementDuringIteration

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ReferenceLastElementDuringIterationUnitTest {

    @Test
    fun `creates new list by adding elements from original list using loop`() {
        val list = listOf(1, 2, 3, 4, 5)
        val expectedList = listOf(1, 3, 6, 10, 15)

        assertEquals(expectedList, iterateListUsingLoop(list))
    }

    @Test
    fun `creates new list by adding elements from original list using fold method`() {
        val list = listOf(1, 2, 3, 4, 5)
        val expectedList = listOf(1, 3, 6, 10, 15)

        assertEquals(expectedList, iterateListUsingFold(list))
    }

    @Test
    fun `creates new list by adding elements from original list using reduce method`() {
        val list = listOf(1, 2, 3, 4, 5)
        val expectedList = listOf(1, 3, 6, 10, 15)

        assertEquals(expectedList, iterateListUsingSequence(list))
    }

    @Test
    fun `creates new list by adding elements from original list using runningReduce method`() {
        val list = listOf(1, 2, 3, 4, 5)
        val expectedList = listOf(1, 3, 6, 10, 15)
        val result =  list.runningReduce { acc, current -> acc + current }

        assertEquals(expectedList, result)
    }

    @Test
    fun `creates new list by adding elements from original list using scan method`() {
        val list = listOf(1, 2, 3, 4, 5)
        val expectedList = listOf(1, 3, 6, 10, 15)
        val result =  list.scan(0) { acc, current -> acc + current }.drop(1)

        assertEquals(expectedList, result)
    }
}
fun iterateListUsingLoop(list: List<Int>): List<Int> {
    val newList = mutableListOf<Int>()
    newList.add(list[0])
    for (i in 1 until list.size) {
        newList.add(list[i] + newList[i - 1])
    }
    return newList
}
fun iterateListUsingFold(list: List<Int>): List<Int> {
    return list.fold(listOf()) { acc, next ->
        if (acc.isEmpty()) {
            acc + next
        } else {
            acc + (acc.last() + next)
        }
    }
}
fun iterateListUsingSequence(list: List<Int>): List<Int> {
    return sequence {
        var sum = 0
        for (element in list) {
            sum += element
            yield(sum)
        }
    }.toList()
}