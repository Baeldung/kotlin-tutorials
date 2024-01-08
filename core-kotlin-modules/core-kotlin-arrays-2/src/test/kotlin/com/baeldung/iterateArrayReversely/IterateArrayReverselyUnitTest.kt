package com.baeldung.iterateArrayReversely

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class IterateArrayReverselyUnitTest {
    private val array = arrayOf("a", "b", "c", "d", "e", "f")
    private val reversedList = listOf("f", "e", "d", "c", "b", "a")

    @Test
    fun `when using reversed() on index Range, then get expected result`() {
        val resultList = mutableListOf<String>()

        for (i in array.indices.reversed()) {
            resultList += array[i]
        }

        assertEquals(reversedList, resultList)
    }

    @Test
    fun `when using downTo on indexes, then get expected result`() {
        val resultList = mutableListOf<String>()
        for (i in (array.lastIndex downTo 0)) {
            resultList += array[i]
        }
        assertEquals(reversedList, resultList)
    }

    @Test
    fun `when using reversed() on the array, then get expected result`() {
        val resultList = mutableListOf<String>()
        for (element in array.reversed()) {
            resultList += element
        }
        assertEquals(reversedList, resultList)
    }
}