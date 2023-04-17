package com.baeldung.collection_sequence_difference

import org.junit.Assert.assertEquals
import org.junit.Test

class CollectionAndSequenceDifferenceTest {

    //eagerly and lazy evaluation
    @Test
    fun `collection eagerly iterate through all the items in map`() {
        var mapIterations = 0
        (1..100)
                .map {
                    mapIterations++
                    it * it
                }
                .filter { it % 2 == 0 }
                .first { it > 50 }
        assertEquals(mapIterations, 100)
    }

    @Test
    fun `sequence lazily iterate through only items in map that matches all the condition`() {
        var mapIterations = 0
        (1..100)
                .asSequence()
                .map {
                    mapIterations++
                    it * it
                }
                .filter { it % 2 == 0 }
                .first { it > 50 }
        assertEquals(mapIterations, 8)
    }

    //Sequence needs terminal operation
    @Test
    fun `sequence does not evaluate if there is no terminal operation`() {
        var isEvaluated = false
        val evens = sequenceOf(1, 2, 3, 4, 5)
                .filter { it % 2 == 0 }
                .map { it * it }

        evens.onEach {
            isEvaluated = true
        }
        assertEquals(isEvaluated, false)
    }

    @Test
    fun `sequence evaluate if there is a terminal operation`() {
        var isEvaluated = false
        val evens = sequenceOf(1, 2, 3, 4, 5)
                .filter { it % 2 == 0 }
                .map { it * it }
                .toList()

        evens.onEach {
            isEvaluated = true
        }
        assertEquals(isEvaluated, true)
    }

    //Order matter for performance
    @Test
    fun `iterate through 100 item on onEach if it is before filter operation`() {
        var iterations = 0
        (1..100)
                .onEach {
                    iterations++
                }.filter {
                    it % 2 == 0
                }

        assertEquals(iterations, 100)
    }

    @Test
    fun `iterate through 50 item on onEach if it is after filter operation`() {
        var iterations = 0
        (1..100)
                .filter {
                    it % 2 == 0
                }
                .onEach {
                    iterations++
                }

        assertEquals(iterations, 50)
    }
}