package com.baeldung.mapNotNullGet

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.*

class NotNullMap<K, V>(private val map: Map<K, V>) : Map<K, V> by map {
    override operator fun get(key: K): V {
        return map[key]!! // throws nullPointerException if the key doesn't exist
    }
}

// Provides the same factory function for creating a NotNullMap just like creating Map by mapOf()
fun <K, V> notNullMapOf(vararg pairs: Pair<K, V>) = NotNullMap(mapOf(*pairs))

class MapNotNullGetUnitTest {
    val inputMap = mapOf(
      1 to "Macbook pro, MacOS",
      2 to "Dell Laptop, Windows10",
      3 to "HP Laptop, Archlinux",
      4 to "Thinkpad Laptop, Ubuntu Linux",
    )

    @Test
    fun `when get value using the get operator then return a nullable value`() {
        val result = inputMap[3]
        assertTrue { result!!.startsWith("HP Laptop") }

        val nullResult = inputMap[42]
        assertNull(nullResult)
    }

    @Test
    fun `when get value using the getValue() then return a not-null value`() {
        val result = inputMap.getValue(3)
        assertTrue { result.startsWith("HP Laptop") }

        assertThrows<NoSuchElementException> { inputMap.getValue(42) }
    }

    @Test
    fun `when wrapping the map with an anonymous object then return a not-null value`() {
        val laptops = object {
            val theMap = inputMap
            operator fun get(key: Int): String = theMap[key]!!
        }

        val result = laptops[3]
        assertTrue { result.startsWith("HP Laptop") }
        assertThrows<NullPointerException> { laptops[42] }

        assertIsNot<Map<String, String>>(laptops)
    }

    @Test
    fun `when using NonNullMap then return a not-null value`() {
        val laptops = NotNullMap(inputMap)
        assertTrue { laptops[3].startsWith("HP Laptop") }
        assertThrows<NullPointerException> { laptops[42] }
        assertIs<Map<Int, String>>(laptops)
        assertEquals(4, laptops.size)

        val personHobbies = notNullMapOf(
          "Tom" to setOf("Reading", "hiking"),
          "Jackson" to setOf("Reading", "Football"),
          "Anna" to setOf("Singing", "Tennis")
        )

        assertEquals(2, personHobbies["Tom"].size)
        assertThrows<NullPointerException> { personHobbies["Shirley"] }


    }
}