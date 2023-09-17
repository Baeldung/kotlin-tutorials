package com.baeldung.pair

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class PairUnitTest{

    @Test
    fun `when have same value then should be equal`(){
        val pair = Pair(first = 1, second = "value")
        val otherPair = Pair(first = 1, second = "value")

        assertEquals(1, pair.first)
        assertEquals("value", pair.second)
        assertTrue(pair == otherPair)
    }

    @Test
    fun `when using infix function then should create a Pair instance`(){
        val pairInfixFunction = 1 to "value"

        assertTrue(pairInfixFunction is Pair)
    }

    @Test
    fun `when calls toString() then should pretty print`(){
        val pairToString = Pair(first = "Hello", second = "World")

        assertEquals("(Hello, World)", pairToString.toString())
    }

    @Test
    fun `when destructure then should have two values`(){
        val pair = Pair(first = 1, second = "value")
        val (first, second) = pair

        assertEquals(1, first)
        assertEquals("value", second)
    }

    @Test
    fun `when calls toString() using different objects then should pretty print`(){
        val pairOfObjects = Pair(first = listOf("one","two"), second = IntRange(start = 1, endInclusive =  10))

        assertEquals("([one, two], 1..10)", pairOfObjects.toString())
    }

    @Test
    fun `when calls toList() extension then should create a List`(){
        val pair = Pair(first = "Hello", second = "World")
        val listFromPair = pair.toList()

        assertTrue(listFromPair is List<String>)
        assertEquals(2, listFromPair.size)
        assertEquals("World", listFromPair.sortedDescending().first())
    }

    @Test
    fun `when using with Map then it should works`(){
        val mapOfPairs = mapOf(1 to "one", 2 to "two", 3 to "three")

        assertEquals("{1=one, 2=two, 3=three}", mapOfPairs.toString())
        assertEquals("one", mapOfPairs[1])
        assertEquals("two", mapOfPairs[2])
        assertEquals("three", mapOfPairs[3])
    }

    @Test
    fun `when first is the same then should override in Map`(){
        val mapOfPairs = mapOf(1 to "one", 2 to "two", 1 to "three")

        assertEquals(2, mapOfPairs.size)
        assertEquals("three", mapOfPairs[1])
    }

}
