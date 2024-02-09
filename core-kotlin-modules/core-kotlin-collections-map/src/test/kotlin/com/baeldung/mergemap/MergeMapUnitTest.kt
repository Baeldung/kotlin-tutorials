package com.baeldung.mergemap

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MergeMapUnitTest {

    @Test
    fun `Return new merged map after merging two maps with plus operator`() {
        val map1 = mapOf("a" to 1, "b" to 2)
        val map2 = mapOf("b" to 3, "c" to 4)

        val mergedMap = map1 + map2

        assertEquals(mapOf("a" to 1, "b" to 3, "c" to 4), mergedMap)
    }

    @Test
    fun `First map contains result of merging with second map using the putAll method`() {
        val map1 = mutableMapOf("a" to 1, "b" to 2)
        val map2 = mapOf("b" to 3, "c" to 4)

        map1.putAll(map2)

        assertEquals(mapOf("a" to 1, "b" to 3, "c" to 4), map1)
    }

    @Test
    fun `Return new merged map after merging two maps with plus assign operator`() {
        val map1 = mutableMapOf("a" to 1, "b" to 2)
        val map2 = mapOf("b" to 3, "c" to 4)

        map1 += map2

        assertEquals(mapOf("a" to 1, "b" to 3, "c" to 4), map1)
    }

    @Test
    fun `Return new merged map after merging two maps using assicateWith function`() {
        val map1 = mapOf("a" to 1, "b" to 2)
        val map2 = mapOf("b" to 3, "c" to 4)

        val mergedMap = (map1.keys + map2.keys).associateWith {
          key -> map2[key] ?: map1[key]!!
        }

        assertEquals(mapOf("a" to 1, "b" to 3, "c" to 4), mergedMap)
    }

    @Test
    fun `Second map contains result of merging two maps using Java Map merge function`() {
        val map1 = mutableMapOf("a" to 1, "b" to 2)
        val map2 = mapOf("b" to 3, "c" to 4)

        map2.forEach { (key, value) ->
            map1.merge(key, value) { oldVal, newVal -> newVal * oldVal }
        }

        assertEquals(mapOf("a" to 1, "b" to 6, "c" to 4), map1)
    }

}
