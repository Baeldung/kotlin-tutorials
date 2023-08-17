package com.baeldung

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test


class SetAndMutableSetUnitTest {

    @Test
    fun testSetCreation() {
        val numbers = setOf(1, 2, 3, 4, 5)

        assertEquals(5, numbers.size)
        assertTrue(numbers.contains(3))
    }

    @Test
    fun testMutableSetCreation() {
        val mutableNumbers = mutableSetOf(1, 2, 3)

        assertEquals(3, mutableNumbers.size)
        assertTrue(mutableNumbers.contains(2))
    }

    @Test
    fun testMembershipCheck() {
        val colors = setOf("red", "green", "blue")

        assertTrue(colors.contains("green"))
        assertFalse(colors.contains("yellow"))
    }

    @Test
    fun testIteratingThroughSet() {
        val numbers = setOf(1, 2, 3, 4, 5)
        val expectedNumbers = setOf(1, 2, 3, 4, 5)

        for (number in numbers) {
            assertTrue(expectedNumbers.contains(number))
        }
    }

    @Test
    fun testSetOperations() {
        val setA = setOf(1, 2, 3)
        val setB = setOf(3, 4, 5)

        val intersection = setA.intersect(setB)
        assertEquals(setOf(3), intersection)

        val union = setA.union(setB)
        assertEquals(setOf(1, 2, 3, 4, 5), union)

        val modifiedSet = setA.plus(6)
        assertEquals(setOf(1, 2, 3, 6), modifiedSet)

        val removedSet = setA.minus(2)
        assertEquals(setOf(1, 3), removedSet)
    }

    @Test
    fun testEqualsAndHashCode() {
        val setA = setOf(1, 2, 3)
        val setB = setOf(3, 2, 1)

        assertEquals(setA, setB)
        assertEquals(setA.hashCode(), setB.hashCode())
    }

    @Test
    fun testToString() {
        val numbers = setOf(1, 2, 3)
        val expectedString = "[1, 2, 3]"

        assertEquals(expectedString, numbers.toString())
    }

    @Test
    fun testAddingElementsToMutableSet() {
        val colors = mutableSetOf("red", "green", "blue")
        colors.add("yellow")

        assertEquals(4, colors.size)
        assertTrue(colors.contains("yellow"))
    }

    @Test
    fun testRemovingElementsFromMutableSet() {
        val colors = mutableSetOf("red", "green", "blue")
        colors.remove("green")

        assertEquals(2, colors.size)
        assertFalse(colors.contains("green"))
    }

    @Test
    fun testSetMembershipCheck() {
        val colors = setOf("red", "green", "blue")

        assertTrue(colors.contains("red"))
        assertFalse(colors.contains("yellow"))
    }

    @Test
    fun testConversionFromMutableSetToImmutableSet() {
        val mutableColors = mutableSetOf("red", "green", "blue")
        val immutableColors: Set<String> = mutableColors.toSet()

        assertEquals(3, immutableColors.size)
        assertTrue(immutableColors.contains("red"))
        assertTrue(immutableColors.contains("green"))
        assertTrue(immutableColors.contains("blue"))
    }

    @Test
    fun testConversionFromImmutableSetToMutableSet() {
        val immutableColors = setOf("red", "green", "blue")
        val mutableColors: Set<String> = immutableColors.toMutableSet()

        assertEquals(3, mutableColors.size)
        assertTrue(mutableColors.contains("red"))
        assertTrue(mutableColors.contains("green"))
        assertTrue(mutableColors.contains("blue"))
    }
}