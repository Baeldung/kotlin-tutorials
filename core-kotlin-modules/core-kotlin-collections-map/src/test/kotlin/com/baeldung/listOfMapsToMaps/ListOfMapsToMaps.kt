package com.baeldung.listOfMapsToMaps

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ListOfMapsToMaps {

    @Test
    fun `converts list of maps to maps grouped by key using for loop`() {
        val listOfMaps = listOf(
            mapOf("name" to "Albert", "age" to "18"),
            mapOf("name" to "Naomi", "age" to "26"),
            mapOf("name" to "Dru", "age" to "18"),
            mapOf("name" to "Steve", "age" to "30")
        )
        val expectedMap = mapOf(
            "name" to listOf("Albert", "Naomi", "Dru", "Steve"),
            "age" to listOf("18", "26", "18", "30")
        )

        assertEquals(expectedMap, groupByUsingForLoop(listOfMaps))
    }

    @Test
    fun `converts list of maps to maps grouped by key using groupBy method`() {
        val listOfMaps = listOf(
            mapOf("name" to "Albert", "age" to "18"),
            mapOf("name" to "Naomi", "age" to "26"),
            mapOf("name" to "Dru", "age" to "18"),
            mapOf("name" to "Steve", "age" to "30")
        )
        val expectedMap = mapOf(
            "name" to listOf("Albert", "Naomi", "Dru", "Steve"),
            "age" to listOf("18", "26", "18", "30")
        )

        val result = listOfMaps
            .flatMap { map -> map.entries }
            .groupBy({ it.key }, { it.value })

        assertEquals(expectedMap, result)
    }

    @Test
    fun `converts list of maps to maps grouped by key using fold method`() {
        val listOfMaps = listOf(
            mapOf("name" to "Albert", "age" to "18"),
            mapOf("name" to "Naomi", "age" to "26"),
            mapOf("name" to "Dru", "age" to "18"),
            mapOf("name" to "Steve", "age" to "30")
        )
        val expectedMap = mapOf(
            "name" to listOf("Albert", "Naomi", "Dru", "Steve"),
            "age" to listOf("18", "26", "18", "30")
        )


        assertEquals(expectedMap, groupByUsingFoldMethod(listOfMaps))
    }
}

fun groupByUsingForLoop(input: List<Map<String, String>>): Map<String, List<String>> {
    val result = mutableMapOf<String, MutableList<String>>()
    for (map in input) {
        for ((key, value) in map) {
            result.getOrPut(key) { mutableListOf() }.add(value)
        }
    }
    return result
}
fun groupByUsingFoldMethod(input: List<Map<String, String>>): Map<String, List<String>> {
    return input.fold(emptyMap()) { acc, map ->
        acc.keys.union(map.keys).associateWith { key ->
            acc.getOrDefault(key, emptyList()) + map.getOrDefault(key, "")
        }
    }
}


