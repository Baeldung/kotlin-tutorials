package com.baeldung.random

import org.junit.jupiter.api.Test
import java.util.concurrent.ThreadLocalRandom
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull

class RandomListElementsUnitTest {

    @Test
    fun randomElementFromGivenList() {

        val list = listOf("one", "two", "three", "four", "five")
        val randomElement = list.random()

        assertNotNull(randomElement)
    }

    @Test
    fun randomElementUsingSequences() {

        val list = listOf("one", "two", "three", "four", "five")
        val randomElement = list.asSequence().shuffled().find { true }

        assertNotNull(randomElement)
    }

    @Test
    fun randomElementsFromGivenList() {

        val list = listOf("one", "two", "three", "four", "five")
        val numberOfElements = 2

        val randomElements = list.asSequence().shuffled().take(numberOfElements).toList()

        assertNotNull(randomElements)
        assertEquals(list.size, 5)
    }

    @Test
    fun randomElementsFromGivenMutableList() {

        val list = mutableListOf("one", "two", "three", "four", "five")
        val numberOfElements = 2

        val randomElements = list.asSequence().shuffled().take(numberOfElements).toList()
        list.removeIf { i-> randomElements.contains(i) }

        assertFalse { randomElements.isNullOrEmpty() }
        assertEquals(list.size, 3)
    }


    @Test
    fun randomElementMultithreaded() {

        val list = listOf("one", "two", "three", "four", "five")
        val randomIndex: Int = ThreadLocalRandom.current().nextInt(list.size)
        val randomElement = list[randomIndex]

        assertNotNull(randomElement)
    }
}