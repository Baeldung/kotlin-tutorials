package com.baeldung.removeFirstElement

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class RemoveFirstElementTest {

    @Test
    fun `Remove first element by creating a slice`() {
        val array = arrayOf("Apple", "Banana", "Cherry")

        val updatedArray = array.slice(1 until array.size)

        assertEquals(listOf("Banana", "Cherry"), updatedArray)
    }

    @Test
    fun `Remove first element by using drop`() {
        val array = arrayOf("Apple", "Banana", "Cherry")

        val updatedArray = array.drop(1)

        assertEquals(arrayOf("Banana", "Cherry"), updatedArray)
    }

    @Test
    fun `Remove first element by using filterIndexed`() {
        val array = arrayOf("Apple", "Banana", "Cherry")

        val updatedArray = array.filterIndexed { index, _ -> index != 0 }

        assertEquals(arrayOf("Banana", "Cherry"), updatedArray)
    }

}