package com.baeldung.collectionToArrayList

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.collections.ArrayList

class CollectionToArrayListUnitTest {

    @Test
    fun `converts a Collection to ArrayList using the for loop and add method`() {
        val collection = listOf("Kotlin", "Java", "Scala")
        val arrayList = ArrayList<String>()
        for (element in collection) {
            arrayList.add(element)
        }

        assertEquals(arrayListOf("Kotlin", "Java", "Scala"), arrayList)
    }

    @Test
    fun `converts a Collection to ArrayList using the ArrayList constructor`() {
        val collection = listOf("Kotlin", "Java", "Scala")
        val arrayList = ArrayList(collection)

        assertEquals(arrayListOf("Kotlin", "Java", "Scala"), arrayList)
    }

    @Test
    fun `converts a Collection to ArrayList using the toCollection method`() {
        val collection = listOf("Kotlin", "Java", "Scala")
        val arrayList = collection.toCollection(ArrayList())

        assertEquals(arrayListOf("Kotlin", "Java", "Scala"), arrayList)
    }

    @Test
    fun `converts a Collection to ArrayList using the addAll method`() {
        val collection = listOf("Kotlin", "Java", "Scala")
        val arrayList = ArrayList<String>()
        arrayList.addAll(collection)

        assertEquals(arrayListOf("Kotlin", "Java", "Scala"), arrayList)
    }

    @Test
    fun `converts a Collection to ArrayList using the set method`() {
        val collection = listOf("Kotlin", "Java", "Scala")
        val arrayList = ArrayList(List(collection.size) { "" })
        collection.forEachIndexed { index, element -> arrayList.set(index, element) }

        assertEquals(arrayListOf("Kotlin", "Java", "Scala"), arrayList)
    }
}