package com.baeldung.arrayToList

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.util.Arrays
import org.junit.jupiter.api.Test


class ConvertArrayToListUnitTest {
    private val givenArray = arrayOf("one", "two", "three", "four", "five")
    private val expectedList = listOf("one", "two", "three", "four", "five")


    @Test
    fun `Given an array, when call Java Arrays' asList() method, should get the expected list`() {
        val myList = Arrays.asList(givenArray)
        assertThat(myList).isEqualTo(expectedList)
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