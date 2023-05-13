package com.baeldung.reverseMap

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals
import kotlin.test.assertTrue


class ReversingMapUnitTest {
    val priceMap = mapOf(
      "Apple" to 1.99,
      "Orange" to 3.49,
      "Milk" to 1.79,
      "Pizza" to 4.99,
    )

    val expectedMap = mapOf(
      1.99 to "Apple",
      3.49 to "Orange",
      1.79 to "Milk",
      4.99 to "Pizza",
    )

    val priceMap2 = mapOf(
      "Apple" to 1.99,
      "Orange" to 3.49,
      "Milk" to 1.79,
      "Pizza" to 4.99,
      "Egg" to 1.99,
      "Strawberry" to 3.49,
      "Chicken" to 4.99,
      "Grape" to 4.99,
    )

    val expectedMap2 = mapOf(
      1.99 to listOf("Apple", "Egg"),
      3.49 to listOf("Orange", "Strawberry"),
      1.79 to listOf("Milk"),
      4.99 to listOf("Pizza", "Chicken", "Grape"),
    )

    @Test
    fun `when reverse a map using map() and toMap() then get expected result`() {
        val result = priceMap.map { (k, v) -> v to k }.toMap()
        assertEquals(expectedMap, result)
    }

    @Test
    fun `when reverse a map using entry-associateBy() then get expected result`() {
        val result = priceMap.entries.associateBy({ it.value }) { it.key }
        assertEquals(expectedMap, result)
    }

    @Test
    fun `when reverse a map using entry-associate() then get expected result`() {
        val result = priceMap.entries.associate { (k, v) -> v to k }
        assertEquals(expectedMap, result)
    }

    @Test
    fun `when reverse a map using for-each then get expected result`() {
        val result = mutableMapOf<Double, String>().apply {
            priceMap.forEach { (k, v) -> put(v, k) }
        }.toMap()
        assertEquals(expectedMap, result)
    }

    //a map contains duplicate values

    @Test
    fun `when reverse a map with duplicate values then the key will be overwritten`() {
        val result = priceMap2.entries.associate { (k, v) -> v to k }
        val overwrittenMap = mapOf(
          1.99 to "Egg",
          3.49 to "Strawberry",
          1.79 to "Milk",
          4.99 to "Grape",
        )
        assertEquals(overwrittenMap, result)
    }

    fun <K, V> Map<K, V>.reverse(): Result<Map<V, K>> {
        return runCatching {
            this.entries.associate { (k, v) -> v to k }
              .also { reversedMap -> require(this.size == reversedMap.size, { "Reversing failed, the map contains duplicated values" }) }
        }
    }

    @Test
    fun `when reverse a map with or without duplicate values then get expected result`() {
        val result1 = priceMap.reverse()
        assertTrue(result1.isSuccess)
        assertEquals(expectedMap, result1.getOrThrow())

        // with duplicate values
        val result2 = priceMap2.reverse()

        assertTrue(result2.isFailure)
        assertThrows<IllegalArgumentException> { result2.getOrThrow() }
    }

    @Test
    fun `when reverse a map with duplicate values using groupBy then get expected result`() {
        val result = priceMap2.toList().groupBy { thePair -> thePair.second }
          .mapValues { entry -> entry.value.map { it.first } }
        assertEquals(expectedMap2, result)
    }


}