package com.baeldung.deque

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

class ArrayDequeUnitTest {
    // ADDING ELEMENTS

    @Test
    fun `adds element to the front of array deque`() {
        val deque = ArrayDeque(listOf(2, 3, 4))
        deque.addFirst(1)
        assertEquals(1, deque[0])
    }

    @Test
    fun `adds element to the end of array deque`() {
        val deque = ArrayDeque(listOf(2, 3, 4))
        deque.add(5)
        assertEquals(5, deque[deque.lastIndex])
    }

    // REMOVING ELEMENTS

    @Test
    fun `removes first element of an array deque`() {
        val deque = ArrayDeque(listOf(2, 3, 4))
        val removedElement = deque.removeFirst()
        assertEquals(2, removedElement)
    }

    @Test
    fun `throws NoSuchElementException exception when removing first element from an empty array deque`() {
        val deque = ArrayDeque<Int>()
        assertThrows<NoSuchElementException> {
            deque.removeFirst()
        }
    }

    @Test
    fun `returns null when removing first element from an empty array deque`() {
        val deque = ArrayDeque<Int>()
        val removedElement = deque.removeFirstOrNull()
        assertEquals(null, removedElement)
    }

    @Test
    fun `removes last element of an array deque`() {
        val deque = ArrayDeque(listOf(2, 3, 4))
        val removedElement = deque.removeLast()
        assertEquals(4, removedElement)
    }

    @Test
    fun `throws NoSuchElementException exception when removing last element from an empty array deque`() {
        val deque = ArrayDeque<Int>()
        assertThrows<NoSuchElementException> {
            deque.removeLast()
        }
    }

    @Test
    fun `returns null when removing last element from an empty array deque`() {
        val deque = ArrayDeque<Int>()
        val removedElement = deque.removeLastOrNull()
        assertEquals(null, removedElement)
    }

    // RETRIEVING ELEMENTS

    @Test
    fun `retrieves first element of an array deque`() {
        val deque = ArrayDeque(listOf(2, 3, 4))
        val firstElement = deque.first()
        assertEquals(2, firstElement)
    }

    @Test
    fun `throws NoSuchElementException exception when retrieving first element from an empty array deque`() {
        val deque = ArrayDeque<Int>()
        assertThrows<NoSuchElementException> {
            deque.first()
        }
    }

    @Test
    fun `returns null when retrieving first element from an empty array deque`() {
        val deque = ArrayDeque<Int>()
        val firstElement = deque.firstOrNull()
        assertEquals(null, firstElement)
    }

    @Test
    fun `retrieves element at a specific index or throws IndexOutOfBoundsException`() {
        val deque = ArrayDeque(listOf(2, 3, 4))
        val secondElement = deque.get(1)
        assertEquals(3, secondElement)
        assertThrows<IndexOutOfBoundsException> { deque.get(4) }
    }
}