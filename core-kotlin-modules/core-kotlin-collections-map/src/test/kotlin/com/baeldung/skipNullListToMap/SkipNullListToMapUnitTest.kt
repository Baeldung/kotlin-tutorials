package com.baeldung.skipNullListToMap

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SkipNullListToMapUnitTest {

    @Test
    fun `Skips null values using for loop`() {
        val pairs = listOf<Pair<String, Int?>>(Pair("a", 1), Pair("b", null), Pair("c", 3))
        val expected = mapOf("a" to 1, "c" to 3)
        val map = mutableMapOf<String, Int>()
        for (pair in pairs) {
            if (pair.second != null) {
                map[pair.first] = pair.second!!
            }
        }
        assertEquals(expected, map)
    }

    @Test
    fun `Skips null values using the filter method`() {
        val list = listOf<Pair<String, Int?>>(Pair("a", 1), Pair("b", null), Pair("c", 3))
        val filteredList = list.filter { it.second != null }
        val map = filteredList.toMap()

        val expected = mapOf("a" to 1, "c" to 3)
        assertEquals(expected, map)
    }

    @Test
    fun `Skips null values using the mapNotNull method`() {
        val pairs = listOf<Pair<String, Int?>>(Pair("a", 1), Pair("b", null), Pair("c", 3))
        val expected = mapOf("a" to 1, "c" to 3)
        val result = pairs.mapNotNull { it.second?.let { value -> it.first to value } }.toMap()

        assertEquals(expected, result)
    }

    @Test
    fun `Skips null values using the fold method`() {
        val pairs = listOf<Pair<String, Int?>>(Pair("a", 1), Pair("b", null), Pair("c", 3))
        val expected = mapOf("a" to 1, "c" to 3)
        val map = pairs.fold(mutableMapOf<String, Int>()) { acc, pair ->
            acc.apply {
                if (pair.second != null) {
                    put(pair.first, pair.second!!)
                }
            }
        }
        assertEquals(expected, map)
    }
}