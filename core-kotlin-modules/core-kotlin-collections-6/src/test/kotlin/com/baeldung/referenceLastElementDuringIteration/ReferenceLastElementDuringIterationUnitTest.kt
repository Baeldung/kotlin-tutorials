package com.baeldung.referenceLastElementDuringIteration

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ReferenceLastElementDuringIterationUnitTest {

    @Test
    fun `creates new list by adding elements from original list using loop`() {
        val list = listOf(1, 2, 3, 4, 5)
        val expectedList = listOf("1","12", "23", "34", "45")

        assertEquals(expectedList, iterateListUsingLoop(list))
    }

    @Test
    fun `creates new list by adding elements from original list using fold method`() {
        val list = listOf(1, 2, 3, 4, 5)
        val expectedList = listOf("1", "12", "23", "34", "45")

        assertEquals(expectedList, iterateListUsingFoldIndexed(list))
    }
    @Test
    fun `creates new list by adding elements from original list using zipWitNnext method`() {
        val list = listOf(1, 2, 3, 4, 5)
        val expectedList = listOf("1", "12", "23", "34", "45")

        val result = mutableListOf<String>()
        result.add(list[0].toString())
        result.addAll(list.zipWithNext { a, b -> "$a$b" })

        assertEquals(expectedList, result)
    }

    @Test
    fun `creates new list by adding elements from original list using scan method`() {
        val list = listOf(1, 2, 3, 4, 5)
        val expectedList = listOf("1", "12", "23", "34", "45")
        val result = list.drop(1).scan(list.first().toString(), { acc, i -> acc.takeLast(1) + i })

        assertEquals(expectedList, result)
    }
}
fun iterateListUsingLoop(list: List<Int>): List<String> {
    val newList = mutableListOf<String>()
    newList.add(list[0].toString())
    for (i in 1 until list.size) {
        newList.add("${list[i - 1]}${list[i]}")
    }
    return newList
}

fun iterateListUsingFoldIndexed(list: List<Int>): List<String> {
    return list.foldIndexed(mutableListOf()) { i, acc, element ->
        if(i==0)
            acc.add(list[0].toString())
        if (i > 0) {
            acc.add("${list[i - 1]}$element")
        }
        acc
    }
}
fun iterateListUsingSequence(list: List<Int>): List<String> {
    return sequence {
        for (i in 1 until list.size) {
            yield("${list[i - 1]}${list[i]}")
        }
    }.toList()
}