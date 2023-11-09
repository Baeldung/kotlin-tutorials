package com.baeldung.maptransform

import org.junit.Test
import kotlin.test.assertEquals
class MapTransformUnitTest {

    @Test
    fun `transform map using mapValues() method`() {
        val map = mapOf("one" to 1, "two" to 2, "three" to 3, "four" to 4, "five" to 5)
        val transformedMap = map.mapValues { it.value * 2 }

        assertEquals(mapOf("one" to 2, "two" to 4, "three" to 6, "four" to 8, "five" to 10), transformedMap)
    }

    @Test
    fun `transform map using filterKeys() method`() {

        val map1 = mapOf("one" to 1, "two" to 2, "three" to 3, "four" to 4, "five" to 5)
        val map2 = mapOf(1 to 1, 2 to 2, 3 to 3, 4 to 4, 5 to 5)
        
        val transformedMap1 = map1.filterKeys { it != "three" }
        val transformedMap2 = map2.filterKeys { it % 2 == 0 }

        assertEquals(mapOf("one" to 1, "two" to 2, "four" to 4, "five" to 5), transformedMap1)
        assertEquals(mapOf(2 to 2, 4 to 4), transformedMap2)
    }

    @Test
    fun `transform map using mapKeys() method`() {
        val map = mapOf("one" to 1, "two" to 2, "three" to 3, "four" to 4, "five" to 5)
        val transformedMap = map.mapKeys { it.key.uppercase() }

        assertEquals(mapOf("ONE" to 1, "TWO" to 2, "THREE" to 3, "FOUR" to 4, "FIVE" to 5), transformedMap)
    }

    @Test
    fun `transform map using associate() method`() {
        val originalList = listOf("one", "two", "three", "four", "five")
        val transformedMap = originalList.associate { it to it.length }

        assertEquals(mapOf("one" to 3, "two" to 3, "three" to 5, "four" to 4, "five" to 4), transformedMap)
    }

    @Test
    fun `transform map using map() method`() {
        val map = mapOf("one" to 1, "two" to 2, "three" to 3, "four" to 4, "five" to 5)
        val transformedMap = map.map { it.key.uppercase() to it.value * 10 }.toMap()

        assertEquals(mapOf("ONE" to 10, "TWO" to 20, "THREE" to 30, "FOUR" to 40, "FIVE" to 50), transformedMap)
    }

    @Test
    fun `transform map using flatMap() method`() {
        val map = mapOf("one" to listOf(1, 2), "two" to listOf(3, 4, 5), "three" to listOf(6, 7, 8, 9))

        val flattenedList = map.flatMap { (key, value) -> value.map { key to it } }
        val grouped = flattenedList.groupBy({ it.first }, { it.second })

        assertEquals("{one=[1, 2], two=[3, 4, 5], three=[6, 7, 8, 9]}", grouped.toString())
    }
}