package com.baeldung.iterateforloopfromindex
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class IterateForLoopFromIndexPositionUnitTest {

    @Test
    fun `start for loop from given index usign the drop method`() {
        val numbers = listOf(1, 2, 3, 4, 5)
        val startIndex = 2

        val result = numbers.drop(startIndex)

        assertEquals(listOf(3, 4, 5), result)

        assertEquals(listOf(3, 4, 5), result)
    }

    @Test
    fun `start for loop from given index with range operator`() {
        val numbers = listOf(1, 2, 3, 4, 5)
        val startIndex = 2
        val result = mutableListOf<Int>()

        for (i in startIndex..numbers.lastIndex) {
            result.add(numbers[i])
        }

        assertEquals(listOf(3, 4, 5), result)
    }

    @Test
    fun `start for loop from given index using withIndex method`() {
        val numbers = listOf(1, 2, 3, 4, 5)
        val startIndex = 2
        val result = mutableListOf<Pair<Int, Int>>()

        for ((index, value) in numbers.withIndex().drop(startIndex)) {
            result.add(index to value)
        }

        assertEquals(listOf(2 to 3, 3 to 4, 4 to 5), result)
    }

    @Test
    fun `start for loop from given index using withIndex method after drop() method `() {
        val numbers = listOf(1, 2, 3, 4, 5)
        val startIndex = 2
        val result = mutableListOf<Pair<Int, Int>>()

        for ((index, value) in numbers.drop(startIndex).withIndex()) {
            result.add(index to value)
        }

        assertEquals(listOf(0 to 3, 1 to 4, 2 to 5), result)
    }


    @Test
    fun `start for loop from given index using forEachIndexed() method`() {
        val numbers = listOf(1, 2, 3, 4, 5)
        val startIndex = 2
        val result = mutableListOf<Pair<Int, Int>>()

        numbers.drop(startIndex).forEachIndexed { index, value ->
            result.add(index to value)
        }

        assertEquals(listOf(0 to 3, 1 to 4, 2 to 5), result)
    }
}