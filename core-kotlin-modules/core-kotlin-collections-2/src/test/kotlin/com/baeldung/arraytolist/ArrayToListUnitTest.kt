package com.baeldung.arraytolist

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ArrayToListUnitTest {

    private val givenArray = arrayOf("one", "two", "three", "four", "five")
    private val expectedList = listOf("one", "two", "three", "four", "five")

    @Test
    fun `asList maintains a reference to the original array`() {
        val array = intArrayOf(1, 2, 3, 4)
        val list = array.asList()

        assertEquals(listOf(1, 2, 3, 4), list)

        array[0] = 0
        assertEquals(listOf(0, 2, 3, 4), list)
    }

    @Test
    fun `toList copies the original array elements`() {
        val array = intArrayOf(1, 2, 3, 4)
        val list = array.toList()
        val mutable = array.toMutableList()

        assertEquals(listOf(1, 2, 3, 4), list)
        assertEquals(listOf(1, 2, 3, 4), mutable)

        array[0] = 0
        assertEquals(listOf(1, 2, 3, 4), list) // list didn't change

        mutable.add(5)
        assertEquals(listOf(1, 2, 3, 4, 5), mutable)
    }

    @Test
    fun `Given an array, when call listOf() method, should get the expected list`() {
        val myList = listOf(*givenArray)
        assertThat(myList).isEqualTo(expectedList)
    }

    @Test
    fun `Given an array, when call array's toList() function should get the expected list`() {
        val myList = givenArray.toList()
        assertThat(myList).isEqualTo(expectedList)
    }

    @Test
    fun `Given an array, when call array's toCollection(destination) function should get the expected list`() {
        val myList = givenArray.toCollection(ArrayList())
        assertThat(myList).isEqualTo(expectedList)

        val existingList = mutableListOf("I have an element already.")
        val appendedList = givenArray.toCollection(existingList)
        assertThat(appendedList).isEqualTo(mutableListOf("I have an element already.", *givenArray))
    }

}
