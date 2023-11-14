package com.baeldung.removefirstelement

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test

class RemoveFirstElementUnitTest {

    @Test
    fun `Remove first element by creating a slice`() {
        val array = arrayOf("Apple", "Banana", "Cherry")

        val updatedArray = array.sliceArray(1 until array.size)

        assertArrayEquals(arrayOf("Banana", "Cherry"), updatedArray)
    }

    @Test
    fun `Remove first element by using drop`() {
        val array = arrayOf("Apple", "Banana", "Cherry")

        val updatedArray = array.drop(1).toTypedArray()

        assertArrayEquals(arrayOf("Banana", "Cherry"), updatedArray)
    }

    @Test
    fun `Remove first element by using filterIndexed`() {
        val array = arrayOf("Apple", "Banana", "Cherry")

        val updatedArray = array.filterIndexed { index, _ -> index != 0 }.toTypedArray()

        assertArrayEquals(arrayOf("Banana", "Cherry"), updatedArray)
    }

}