package com.baeldung.indexof

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class IndexOfTests {

    @Test
    fun `Find index using indexOf`() {
        val numbers = arrayOf(1, 2, 3, 4, 5)
        val elementToFind = 3
        val index = numbers.indexOf(elementToFind)

        assertEquals(2, index)
    }

    @Test
    fun `Find index of first`() {
        val numbers = arrayOf(1, 2, 3, 4, 3, 5)
        val elementToFind = 3
        val index = numbers.indexOfFirst { it == elementToFind }

        assertEquals(2, index)
    }

    @Test
    fun `Find last index of element`() {
        val numbers = arrayOf(1, 2, 3, 4, 3, 5)
        val elementToFind = 3
        val lastIndex = numbers.lastIndexOf(elementToFind)

        assertEquals(4, lastIndex)
    }

    @Test
    fun `Find index of last`() {
        val numbers = arrayOf(1, 2, 3, 4, 3, 5)
        val elementToFind = 3
        val index = numbers.indexOfLast { it == elementToFind }

        assertEquals(4, index)
    }

    @Test
    fun `Find indices using loop`() {
        val numbers = arrayOf(1, 2, 3, 4, 3, 5)
        val elementToFind = 3
        val indices = mutableListOf<Int>()

        for (i in numbers.indices) {
            if (numbers[i] == elementToFind) {
                indices.add(i)
            }
        }

        assertEquals(listOf(2, 4), indices)
    }

}