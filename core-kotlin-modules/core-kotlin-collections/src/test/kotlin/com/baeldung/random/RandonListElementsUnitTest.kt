package com.baeldung.random

import org.junit.jupiter.api.Test
import java.util.concurrent.ThreadLocalRandom
import kotlin.random.Random
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class RandonListElementsUnitTest {

    var batmans: List<String> = listOf("Christian Bale", "Michael Keaton", "Ben Affleck", "George Clooney")

    @Test
    fun randomElementFromGivenList() {

        val list = listOf(1, 2, 3, 4, 5)
        val randomElement = list.random()

        assertNotNull(randomElement)
    }

    @Test
    fun randomElementFromGivenListWithoutRepetitions() {

        val list = mutableListOf("one", "two", "three", "four", "five")
        val numberOfElements = 2

        for (n in 1..numberOfElements) {

            val randomIndex = Random.nextInt(list.size)
            val randomElement = list[randomIndex]
            list.removeAt(randomIndex)

            assertNotNull(randomElement)
        }

        assertEquals(list.size, 3)
    }

    @Test
    fun randomElementFromGivenListWithRepetitions() {

        val list = listOf("one", "two", "three", "four", "five")

        val numberOfElements = 2

        for (n in 1..numberOfElements) {
            val randomIndex = Random.nextInt(list.size)
            val randomElement = list[randomIndex]

            assertNotNull(randomElement)
        }

        assertEquals(list.size, 5)
    }

    @Test
    fun randomElementUsingSequences() {

        val list = listOf("one", "two", "three", "four", "five")
        val randomElement = list.asSequence().shuffled().find { true }

        assertNotNull(randomElement)
    }

    @Test
    fun randomElementMultithreaded() {

        val list = listOf("one", "two", "three", "four", "five")
        val randomIndex: Int = ThreadLocalRandom.current().nextInt(list.size)
        val randomElement = list[randomIndex]

        assertNotNull(randomElement)
    }

    @Test
    fun randomElementsSeries() {

        val list = listOf("one", "two", "three", "four", "five")
        val randomSeriesLength = 3
        val randomElements = list.shuffled().subList(0, randomSeriesLength)

        assertNotNull(randomElements)
        assert(list.containsAll(randomElements) && randomElements.size == 3)
    }
}