package com.baeldung.CollectionSequenceDifference

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
        assertEquals(100, mapIterations)
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
        assertEquals(8, mapIterations)
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
        assertEquals(false, isEvaluated)
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
        assertEquals(true, isEvaluated)
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

        assertEquals(100, iterations)
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

        assertEquals(50,iterations)
    }
}