package com.baeldung.modifyMapEntryInPlace

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ModifyMapEntryInPlaceUnitTest {

    @Test
    fun `modifies mutable map entry using put method`() {
        val map = mutableMapOf("key1" to "value1", "key2" to "value2")
        map.put("key1", "new value")
        assertEquals("new value", map["key1"])
    }

    @Test
    fun `modifies mutable map entry using assign operator`() {
        val map = mutableMapOf("key1" to "value1", "key2" to "value2")
        map["key1"] = "new value"
        assertEquals("new value", map["key1"])
    }

    @Test
    fun `modifies mutable map entry using replace method`() {
        val map = mutableMapOf("key1" to "value1", "key2" to "value2")
        map.replace("key1", "new value")
        assertEquals("new value", map["key1"])
    }


    @Test
    fun `modifies mutable map entry using compute method`() {
        val map = mutableMapOf("key1" to "value1", "key2" to "value2")
        map.compute("key1") { _, _ -> "new value" }
        assertEquals("new value", map["key1"])
    }

    @Test
    fun `modifies mutable map entry using computeIfPresent method`() {
        val map = mutableMapOf("key1" to "value1", "key2" to "value2")
        map.computeIfPresent("key1") { key, value ->
            if (value == "value1") "new value"
            else value
        }
        assertEquals("new value", map["key1"])
    }
}