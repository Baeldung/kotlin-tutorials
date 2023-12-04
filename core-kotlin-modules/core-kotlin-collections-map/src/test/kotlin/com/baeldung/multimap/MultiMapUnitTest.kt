package com.baeldung.multimap

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue


class MultiMapUnitTest {

    @Test
    fun `when put one element then it should have size 1`() {
        val multiMap = MultiMap<String, Int>()
        multiMap.put("even", 2)

        assertEquals(1, multiMap["even"].size)
        assertEquals(1, multiMap.size)
    }

    @Test
    fun `if no elements were put then it should have size 0`() {
        val multiMap = MultiMap<String, Int>()

        assertEquals(0, multiMap.size)
    }

    @Test
    fun `when put two elements with different keys then it should return two keys`() {
        val multiMap = MultiMap<String, Int>()
        multiMap.put("even", 2)
        multiMap.put("odd", 1)

        assertEquals(hashSetOf("even", "odd"), multiMap.keys)
    }

    @Test
    fun `when put two elements with different keys then it should return two entries`() {
        val multiMap = MultiMap<String, Int>()
        multiMap.put("even", 2)
        multiMap.put("odd", 1)

        assertEquals(2, multiMap.entries.size)
    }

    @Test
    fun `when put two elements with different keys then it should return two values`() {
        val multiMap = MultiMap<String, Int>()
        multiMap.put("even", 2)
        multiMap.put("odd", 1)

        assertEquals(hashSetOf(1, 2), multiMap.values)
    }

    @Test
    fun `when put two elements with different keys then it should return values of given key`() {
        val multiMap = MultiMap<String, Int>()
        multiMap.put("even", 2)
        multiMap.put("odd", 1)

        assertEquals(hashSetOf(2), multiMap["even"])
    }

    @Test
    fun `when put two elements with same key then it should have one key and two values`() {
        val multiMap = MultiMap<String, Int>()
        multiMap.put("even", 2)
        multiMap.put("even", 4)

        assertEquals(2, multiMap["even"].size)
        assertEquals(1, multiMap.keys.size)
        assertEquals(2, multiMap.values.size)
    }

    @Test
    fun `when put two elements at the same time then it should have one key and two values`() {
        val multiMap = MultiMap<String, Int>()
        multiMap.putAll("even", setOf(2, 4))

        assertEquals(2, multiMap["even"].size)
        assertEquals(1, multiMap.keys.size)
        assertEquals(2, multiMap.values.size)
    }

    @Test
    fun `when put one element and remove the same element then it should be empty for given key`() {
        val multiMap = MultiMap<String, Int>()
        multiMap.put("even", 2)
        multiMap.remove("even", 2)

        assertTrue(multiMap["even"].isEmpty())
    }

    @Test
    fun `when put three element and remove one then it should keep the other two for given key`() {
        val multiMap = MultiMap<String, Int>()
        multiMap.putAll("even", setOf(2, 4, 6))

        multiMap.remove("even", 2)

        assertEquals(2, multiMap["even"].size)
        assertTrue(multiMap["even"].containsAll(setOf(4, 6)))
    }

    @Test
    fun `when put two elements and remove all then it should have zero elements for the given key`() {
        val multiMap = MultiMap<String, Int>()
        multiMap.putAll("even", setOf(2, 4))

        multiMap.removeAll("even")

        assertEquals(0, multiMap["even"].size)
    }

    @Test
    fun `when put one element and replacing it then it should have the new element for given key`() {
        val multiMap = MultiMap<String, Int>()
        multiMap.put("even", 2)
        multiMap.replace("even", 2, 4)

        assertTrue(multiMap["even"].contains(4))
        assertTrue(!multiMap["even"].contains(2))
    }

    @Test
    fun `when replacing a elements not contained for the key then it should not add and return false`() {
        val multiMap = MultiMap<String, Int>()
        multiMap.put("even", 2)
        val wasReplaced = multiMap.replace("even", 4, 6)

        assertFalse(wasReplaced)
        assertFalse(multiMap["even"].contains(4))
        assertTrue(multiMap["even"].contains(2))
    }

    @Test
    fun `when put two elements and replacing it then it should have the new element for given key`() {
        val multiMap = MultiMap<String, Int>()
        multiMap.putAll("even", setOf(2, 4))
        multiMap.replaceAll("even", setOf(6))

        assertTrue(multiMap["even"].contains(6))
        assertTrue(multiMap["even"].contains(2).not())
        assertTrue(multiMap["even"].contains(4).not())
    }

    @Test
    fun `when put one element then it should contain the key`() {
        val multiMap = MultiMap<String, Int>()
        multiMap.put("even", 2)

        assertTrue(multiMap.containsKey("even"))
    }

    @Test
    fun `when put one element then it should contain the value`() {
        val multiMap = MultiMap<String, Int>()
        multiMap.put("even", 2)

        assertTrue(multiMap.containsValue(2))
    }

    @Test
    fun `when no element was added then it should be empty`() {
        val multiMap = MultiMap<String, Int>()

        assertTrue(multiMap.isEmpty())
    }

    @Test
    fun `when put one element and call clear then it should be empty`() {
        val multiMap = MultiMap<String, Int>()
        multiMap.put("even", 2)
        multiMap.clear()

        assertTrue(multiMap["even"].isEmpty())
    }

}