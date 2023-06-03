package com.baeldung.comparable

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

class IteratorSortingUnitTest {

    class Version(val value: Int) : Comparable<Version> {
        override fun compareTo(other: Version): Int = value compareTo other.value

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Version

            return value == other.value
        }

        override fun hashCode(): Int {
            return value
        }
    }

    private val underTest = setOf(Version(7), Version(9), Version(20), Version(1), Version(0))

    @Test
    fun whenSortedList_thenCorrect() {
        val theList = underTest.toList()
        val sorted = theList.sorted()
        assertTrue(sorted !is Set<*>)
        assertFalse(sorted === theList)
        assertEquals(Version(0), sorted[0])
        assertEquals(Version(1), sorted[1])
        assertEquals(Version(7), sorted[2])
        assertEquals(Version(9), sorted[3])
        assertEquals(Version(20), sorted[4])
    }

    @Test
    fun whenSorted_thenCorrect() {
        val sorted = underTest.sorted()
        assertTrue(sorted !is Set<*>)
        assertEquals(Version(0), sorted[0])
        assertEquals(Version(1), sorted[1])
        assertEquals(Version(7), sorted[2])
        assertEquals(Version(9), sorted[3])
        assertEquals(Version(20), sorted[4])
    }

    @Test
    fun whenSortedDescending_thenCorrect() {
        val sorted = underTest.sortedDescending()
        assertTrue(sorted !is Set<*>)
        assertEquals(Version(20), sorted[0])
        assertEquals(Version(9), sorted[1])
        assertEquals(Version(7), sorted[2])
        assertEquals(Version(1), sorted[3])
        assertEquals(Version(0), sorted[4])
    }

}