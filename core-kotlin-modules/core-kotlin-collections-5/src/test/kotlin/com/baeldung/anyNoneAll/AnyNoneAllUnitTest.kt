package com.baeldung.anyNoneAll

import org.junit.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class AnyNoneAllUnitTest {
    @Test
    fun `check if no element matches the condition using none`() {
        val numbers = listOf(1, 2, 3, 4, 5)
        val noZeroes = numbers.none { it == 0 }
        assertTrue(noZeroes)
    }

    @Test
    fun `none on empty list returns true`() {
        val numbers: List<Int> = listOf()
        val noZeroes = numbers.none { it == 0 }
        assertTrue(noZeroes)
    }

    @Test
    fun `check if atleast one element matches the condition using any`() {
        val numbers = listOf(1, 2, 3, 4, 5, 6)
        val hasEven = numbers.any { it % 2 == 0 }
        assertTrue(hasEven)
    }

    @Test
    fun `return false if no element matches predicate in any`() {
        val numbers = listOf(1, 2, 3, 4, 5, 6)
        val has100plus = numbers.any { it > 100 }
        assertFalse(has100plus)
    }

    @Test
    fun `any on empty list returns false`() {
        val numbers: List<Int> = listOf()
        val hasAnyZeroes = numbers.any { it == 0 }
        assertFalse(hasAnyZeroes)
    }

    @Test
    fun `check if all elements matches predicate in all`() {
        val numbers = listOf(1, 2, 3, 4, 5, 6)
        val allPositive = numbers.all { it > 0 }
        assertTrue(allPositive)
    }

    @Test
    fun `return false if atleast one doesnt match predicate in all`() {
        val numbers = listOf(1, 2, 3, 4, 5, 6)
        val noMultipleOf5 = numbers.all { it % 5 != 0 }
        assertFalse(noMultipleOf5)
    }

    @Test
    fun `all on empty list returns true (Vacuous truth)`() {
        val numbers: List<Int> = listOf()
        val hasAnyZeroes = numbers.all { it != 0 }
        assertTrue(hasAnyZeroes)
    }

    @Test
    fun `check handling of null elements in any`() {
        val numbers = listOf(1, 2, null, 4, 5)
        val hasNull = numbers.any { it == null }
        assertTrue(hasNull)
    }

    @Test
    fun `check handling of null elements in none`() {
        val numbers = listOf(1, 2, null, 4, 5)
        val hasNull = numbers.none { it == null }
        assertFalse(hasNull)
    }

    @Test
    fun `check handling of null elements in all`() {
        val numbers: List<Int?> = listOf(null, null, null)
        val allNull = numbers.all { it == null }
        assertTrue(allNull)
    }

}