package com.baeldung.random

import org.junit.jupiter.api.Test
import java.util.concurrent.ThreadLocalRandom
import kotlin.test.assertEquals
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
    fun randomElementFromGivenListWithRepetitions() {

        val list = listOf("one", "two", "three", "four", "five")
        val numberOfElements = 2

        for (n in 1..numberOfElements) {

            val randomElement = list.asSequence().shuffled().find { true }

            assertNotNull(randomElement)
        }

        assertEquals(list.size, 5)
    }

    @Test
    fun randomElementFromGivenListWithoutRepetitions() {

        val list = mutableListOf("one", "two", "three", "four", "five")
        val numberOfElements = 2

        for (n in 1..numberOfElements) {

            val randomElement = list.asSequence().shuffled().find { true }
            list.removeAt(list.indexOf(randomElement))

            assertNotNull(randomElement)
        }

        assertEquals(list.size, 3)
    }

    @Test
    fun randomElementsSeries() {

        val list = listOf("one", "two", "three", "four", "five")
        val randomSeriesLength = 3
        val randomElements = list.shuffled().subList(0, randomSeriesLength)

        assertNotNull(randomElements)
        assert(list.containsAll(randomElements) && randomElements.size == 3)
    }

    @Test
    fun randomElementMultithreaded() {

        val list = listOf("one", "two", "three", "four", "five")
        val randomIndex: Int = ThreadLocalRandom.current().nextInt(list.size)
        val randomElement = list[randomIndex]

        assertNotNull(randomElement)
    }
}