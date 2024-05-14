package com.baeldung.concurrentmodificationexception

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.*
import java.util.concurrent.CopyOnWriteArrayList

class ConcurrentModificationExceptionUnitTest {


    @Test
    fun `given list when remove during iteration then throw ConcurrentModificationException`() {
        val numbers = mutableListOf(1, 2, 3, 4, 5)

        assertThrows<ConcurrentModificationException> {
            for (item in numbers) {
                if (item == 3) {
                    numbers.remove(item)
                }
            }
        }
    }

    @Test
    fun `given list when remove with iterator then list is updated`() {
        val numbers = mutableListOf(1, 2, 3, 4, 5)
        val iterator = numbers.iterator()
        while (iterator.hasNext()) {
            val number = iterator.next()
            if (number == 3) {
                iterator.remove()
            }
        }

        assertEquals(listOf(1, 2, 4, 5), numbers)
    }

    @Test
    fun `given list when removeAll by condition then list is updated`() {
        val numbers = mutableListOf(1, 2, 3, 4, 5)
        numbers.removeAll { number -> number == 3 }

        assertEquals(listOf(1, 2, 4, 5), numbers)
    }

    @Test
    fun `given list and copy when modify copy then update list`() {
        var numbers = mutableListOf(1, 2, 3, 4, 5)
        val copyNumbers = numbers.toMutableList()

        for (number in numbers) {
            if (number == 3) {
                copyNumbers.remove(number)
            }
        }

        numbers = copyNumbers

        assertEquals(listOf(1, 2, 4, 5), numbers)
    }

    @Test
    fun `given CopyOnWriteArrayList when remove items then items removed`() {
        val list = CopyOnWriteArrayList(listOf(1, 2, 3, 4, 5))

        for (item in list) {
            if (item == 3) {
                list.remove(item)
            }
        }

        assertEquals(listOf(1, 2, 4, 5), list)
    }

}