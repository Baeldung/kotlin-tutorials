package com.baeldung.removeTheLastElement

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class RemoveTheLastElementUnitTest {
    @Test
    fun `when calling removeLast() on mutableList then get the expected result`() {
        val mutableList = mutableListOf("a", "b", "c", "d", "e")
        mutableList.removeLast()
        assertEquals(listOf("a", "b", "c", "d"), mutableList)

        assertThrows<NoSuchElementException> {
            mutableListOf<String>().removeLast()
        }
    }

    @Test
    fun `when calling removeAt() on mutableList then get the expected result`() {
        val mutableList = mutableListOf("a", "b", "c", "d", "e")
        mutableList.removeAt(mutableList.lastIndex)
        assertEquals(listOf("a", "b", "c", "d"), mutableList)

        val emptyMutableList = mutableListOf<String>()
        assertThrows<IndexOutOfBoundsException> {
            emptyMutableList.removeAt(emptyMutableList.lastIndex)
        }.also {
            assertEquals("Index -1 out of bounds for length 0", it.message)
        }
    }

    @Test
    fun `when calling subList() on read-only list then get the expected result`() {
        val myList = listOf("a", "b", "c", "d", "e")
        val result = myList.subList(0, myList.lastIndex)
        assertEquals(listOf("a", "b", "c", "d"), result)

        val myEmptyList = emptyList<String>()
        assertThrows<IndexOutOfBoundsException> {
            myEmptyList.subList(0, myEmptyList.lastIndex)
        }.also {
            assertEquals("fromIndex: 0, toIndex: -1", it.message)
        }
    }

    @Test
    fun `when calling droplast() on read-only list then get the expected result`() {
        val myList = listOf("a", "b", "c", "d", "e")
        val result = myList.dropLast(1)
        assertEquals(listOf("a", "b", "c", "d"), result)

        val myEmptyList = emptyList<String>()
        assertTrue { myEmptyList.dropLast(1).isEmpty() }
    }
}