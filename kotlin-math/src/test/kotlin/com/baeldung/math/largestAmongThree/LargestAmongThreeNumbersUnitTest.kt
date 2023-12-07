package com.baeldung.math.largestAmongThree

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.math.max
import kotlin.test.assertEquals
import kotlin.test.assertNull

class LargestAmongThreeNumbersUnitTest {
    @Test
    fun `when using if-else checks then find the expected result`() {
        val a = 42
        val b = 100
        val c = -42

        val result = if (a >= b && a >= c) {
            a
        } else if (b >= a && b >= c) {
            b
        } else {
            c
        }
        assertEquals(100, result)
    }

    @Test
    fun `when using when checks then find the expected result`() {
        val (a, b, c) = listOf(42, 100, -42)

        val result = when {
            a >= b && a >= c -> a
            b >= a && b >= c -> b
            else -> c
        }
        assertEquals(100, result)
    }

    @Test
    fun `when first find the max from two then find the expected result`() {
        val (a, b, c) = listOf(42, 100, -42)
        val maxOfAB = if (a >= b) a else b
        val result = if (c >= maxOfAB) c else maxOfAB
        assertEquals(100, result)
    }

    @Test
    fun `when using the max function then find the expected result`() {
        val (a, b, c) = listOf(42, 100, -42)
        val result = max(max(a, b), c)
        assertEquals(100, result)
    }

    @Test
    fun `when using the list-max function then find the expected result`() {
        val nNumbers = listOf(42, 100, -42, 20, 43, -2, 103, 420)
        val result = nNumbers.max()
        assertEquals(420, result)
    }

    @Test
    fun `when list is empty then expected exception raised`() {
        assertThrows<NoSuchElementException> { emptyList<Int>().max() }

        val possibleMax = emptyList<Int>().maxOrNull()
        assertNull(possibleMax)
    }
}