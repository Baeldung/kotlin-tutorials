package com.baeldung.cloneset

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertInstanceOf
import org.junit.jupiter.api.Assertions.assertNotSame
import org.junit.jupiter.api.Test

class CloneSetTest {

    @Test
    fun `Clone set using toSet`() {
        val originalSet = setOf("apple", "banana", "cherry")

        val clonedSet = originalSet.toSet()

        assertEquals(originalSet, clonedSet)
        assertNotSame(originalSet, clonedSet)
    }

    @Test
    fun `Clone set using constructor`() {
        val originalSet = setOf("apple", "banana", "cherry")

        val clonedSet = HashSet(originalSet)

        assertEquals(originalSet, clonedSet)
        assertNotSame(originalSet, clonedSet)
    }

    @Test
    fun `Clone set using toMutableSet`() {
        val originalSet = setOf("apple", "banana", "cherry")
        val clonedSet = originalSet.toMutableSet()

        assertEquals (originalSet, clonedSet)
        assertNotSame(originalSet, clonedSet)

        assertInstanceOf(MutableSet::class.java, clonedSet)
    }
}