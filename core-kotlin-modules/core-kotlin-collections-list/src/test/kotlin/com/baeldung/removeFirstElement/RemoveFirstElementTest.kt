package com.baeldung.removeFirstElement

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class RemoveFirstElementTest {

    @Test
    fun `Remove first element using removeAt`() {
        val arrayList = arrayListOf("Apple", "Banana", "Cherry")

        arrayList.removeAt(0)

        assertEquals(listOf("Banana", "Cherry"), arrayList)
    }

    @Test
    fun `Remove first element using remove`() {
        val arrayList = arrayListOf("Apple", "Banana", "Cherry")

        arrayList.remove("Apple")

        assertEquals(listOf("Banana", "Cherry"), arrayList)
    }

    @Test
    fun `Remove first element using remove and first`() {
        val arrayList = arrayListOf("Apple", "Banana", "Cherry")

        arrayList.remove(arrayList.first())

        assertEquals(listOf("Banana", "Cherry"), arrayList)
    }

    @Test
    fun `Remove first element by creating a sublist`() {
        val arrayList = arrayListOf("Apple", "Banana", "Cherry")

        val updatedList = arrayList.subList(1, arrayList.size)

        assertEquals(listOf("Banana", "Cherry"), updatedList)
    }

    @Test
    fun `Remove first element by using slice`() {
        val arrayList = arrayListOf("Apple", "Banana", "Cherry")
        val updatedList = arrayList.slice(1 until arrayList.size)

        assertEquals(listOf("Banana", "Cherry"), updatedList)
    }

    @Test
    fun `Remove first element by using drop`() {
        val arrayList = arrayListOf("Apple", "Banana", "Cherry")

        val updatedList = arrayList.drop(1)

        assertEquals(listOf("Banana", "Cherry"), updatedList)
    }

    @Test
    fun `Remove first element by using filterIndexed`() {
        val arrayList = arrayListOf("Apple", "Banana", "Cherry")

        val updatedList = arrayList.filterIndexed { index, _ -> index != 0 }

        assertEquals(listOf("Banana", "Cherry"), updatedList)
    }

}