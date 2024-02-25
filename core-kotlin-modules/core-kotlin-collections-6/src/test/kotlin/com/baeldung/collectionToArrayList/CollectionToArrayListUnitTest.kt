package com.baeldung.collectionToArrayList

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.collections.ArrayList

class CollectionToArrayListUnitTest {

    @Test
    fun `converts a Collection to ArrayList using the for loop and add method`() {
        val collection: Collection<String> = listOf("Kotlin", "Java", "Scala")
        val arrayList = ArrayList<String>()
        for (element in collection) {
            arrayList.add(element)
        }

        assertEquals(arrayListOf("Kotlin", "Java", "Scala"), arrayList)
    }

    @Test
    fun `converts a Collection to ArrayList using the ArrayList constructor`() {
        val collection: Collection<String> = listOf("Kotlin", "Java", "Scala")
        val arrayList = ArrayList(collection)

        assertEquals(arrayListOf("Kotlin", "Java", "Scala"), arrayList)
    }

    @Test
    fun `converts a Collection to ArrayList using the toCollection method`() {
        val collection: Collection<String> = listOf("Kotlin", "Java", "Scala")
        val arrayList = collection.toCollection(ArrayList())

        assertEquals(arrayListOf("Kotlin", "Java", "Scala"), arrayList)
    }

    @Test
    fun `converts a Collection to ArrayList using the addAll method`() {
        val collection: Collection<String> = listOf("Kotlin", "Java", "Scala")
        val arrayList = ArrayList<String>()
        arrayList.addAll(collection)

        assertEquals(arrayListOf("Kotlin", "Java", "Scala"), arrayList)
    }

    @Test
    fun `converts a Collection to ArrayList using the mapTo method`() {
        val collection: Collection<String> = listOf("Kotlin", "Java", "Scala")
        val arrayList = ArrayList<String>()
        collection.mapTo(arrayList) { it }

        assertEquals(arrayListOf("Kotlin", "Java", "Scala"), arrayList)
    }
}