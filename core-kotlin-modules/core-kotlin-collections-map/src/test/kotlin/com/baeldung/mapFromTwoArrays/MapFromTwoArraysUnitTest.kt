package com.baeldung.mapFromTwoArrays

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MapFromTwoArraysUnitTest {

    @Test
    fun `creates a map from two arrays using a custom approach`() {
        val keys = arrayOf("a", "b", "c")
        val values = arrayOf(1, 2, 3)

        val map = createMap(keys, values)
        val expected = mapOf("a" to 1, "b" to 2, "c" to 3)

        assertEquals(3, map.size)
        assertEquals(expected, map)
    }

    @Test
    fun `creates a map from two arrays using zip() method`() {
        val keys = arrayOf("a", "b", "c")
        val values = arrayOf(1, 2, 3)

        val map = keys.zip(values).toMap()
        val expected = mapOf("a" to 1, "b" to 2, "c" to 3)

        assertEquals(3, map.size)
        assertEquals(expected, map)
    }

    @Test
    fun `creates a map from two arrays using the associateWith() method`() {
        val keys = arrayOf("a", "b", "c")
        val values = arrayOf(1, 2, 3)

        val map = keys.associateWith { key -> values[keys.indexOf(key)] }
        val expected = mapOf("a" to 1, "b" to 2, "c" to 3)

        assertEquals(3, map.size)
        assertEquals(expected, map)
    }

    @Test
    fun `creates a map from two arrays using the mapIndexed() method`() {
        val keys = arrayOf("a", "b", "c")
        val values = arrayOf(1, 2, 3)

        val pairs = keys.mapIndexed { index, key ->
            key to values[index]
        }

        val map = pairs.toMap()
        val expected = mapOf("a" to 1, "b" to 2, "c" to 3)

        assertEquals(3, map.size)
        assertEquals(expected, map)
    }
}
fun createMap(keys: Array<String>, values: Array<Int>): Map<String, Int> {
    val map = mutableMapOf<String, Int>()
    for (i in keys.indices) {
        map[keys[i]] = values[i]
    }
    return map
}