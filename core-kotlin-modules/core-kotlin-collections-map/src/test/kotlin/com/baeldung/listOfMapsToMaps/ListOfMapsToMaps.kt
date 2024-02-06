package com.baeldung.listOfMapsToMaps

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ListOfMapsToMaps {

    @Test
    fun `converts list of maps to maps grouped by key using for loop`() {
        val listOfMaps = listOf(
            mapOf("name" to "Albert", "age" to 18),
            mapOf("name" to "Naomi", "age" to 26),
            mapOf("name" to "Dru", "age" to 18),
            mapOf("name" to "Steve", "age" to 30)
        )
        val expectedMap = mapOf(
            18 to listOf(
                mapOf("name" to "Albert", "age" to 18),
                mapOf("name" to "Dru", "age" to 18)
            ),
            26 to listOf(
                mapOf("name" to "Naomi", "age" to 26),
            ),
            30 to listOf(
                mapOf("name" to "Steve", "age" to 30),
            )
        )

        assertEquals(expectedMap, groupByUsingForLoop(listOfMaps, "age"))
    }

    @Test
    fun `converts list of maps to maps grouped by key using groupBy method`() {
        val listOfMaps = listOf(
            mapOf("name" to "Albert", "age" to 18),
            mapOf("name" to "Naomi", "age" to 26),
            mapOf("name" to "Dru", "age" to 18),
            mapOf("name" to "Steve", "age" to 30)
        )
        val expectedMap = mapOf(
            18 to listOf(
                mapOf("name" to "Albert", "age" to 18),
                mapOf("name" to "Dru", "age" to 18)
            ),
            26 to listOf(
                mapOf("name" to "Naomi", "age" to 26),
            ),
            30 to listOf(
                mapOf("name" to "Steve", "age" to 30),
            )
        )

        val result = listOfMaps.groupBy { it["age"] }

        assertEquals(expectedMap, result)
    }

    @Test
    fun `converts list of maps to maps grouped by key using fold method`() {
        val listOfMaps = listOf(
            mapOf("name" to "Albert", "age" to 18),
            mapOf("name" to "Naomi", "age" to 26),
            mapOf("name" to "Dru", "age" to 18),
            mapOf("name" to "Steve", "age" to 30)
        )
        val expectedMap = mapOf(
            18 to listOf(
                mapOf("name" to "Albert", "age" to 18),
                mapOf("name" to "Dru", "age" to 18)
            ),
            26 to listOf(
                mapOf("name" to "Naomi", "age" to 26),
            ),
            30 to listOf(
                mapOf("name" to "Steve", "age" to 30),
            )
        )


        assertEquals(expectedMap, groupByUsingFoldMethod(listOfMaps, "age"))
    }
}
fun groupByUsingForLoop(listOfMaps: List<Map<String, Any>>, key: String): Map<Any, List<Map<String, Any>>> {
    val resultMap = mutableMapOf<Any, MutableList<Map<String, Any>>>()
    for (map in listOfMaps) {
        val value = map[key]
        if (resultMap.containsKey(value)) {
            resultMap[value]?.add(map)
        } else {
            resultMap[value as Any] = mutableListOf(map)
        }
    }
    return resultMap
}
fun groupByUsingFoldMethod(maps: List<Map<String, Any>>, key: String): Map<Any, List<Map<String, Any>>> {
    return maps.fold(mutableMapOf<Any, MutableList<Map<String, Any>>>()) { acc, map ->
        val value = map[key] ?: return@fold acc
        acc.getOrPut(value) { mutableListOf() }.add(map)
        acc
    }
}


