package com.baeldung.math.checkfibonacci

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class FibonacciUnitTest {

    @Test
    fun `test checkIfFibonacciUsingRecursion with FibonacciNumber should be true`() {
        assertTrue(checkIfFibonacciUsingRecursion(8))
    }

    @Test
    fun `test checkIfFibonacciUsingRecursion with Non-FibonacciNumber should be false`() {
        assertFalse(checkIfFibonacciUsingRecursion(25))
    }

    @Test
    fun `test checkIfFibonacciUsingPerfectSquareProperty with FibonacciNumber should be true`() {
        assertTrue(checkIfFibonacciUsingPerfectSquareProperty(13))
    }

    @Test
    fun `test checkIfFibonacciUsingPerfectSquareProperty with Non-FibonacciNumber should be false`() {
        assertFalse(checkIfFibonacciUsingPerfectSquareProperty(19))
    }

    @Test
    fun `test checkIfFibonacciUsingIteration with FibonacciNumber should be true`() {
        assertTrue(checkIfFibonacciUsingIteration(13))
    }

    @Test
    fun `test checkIfFibonacciUsingIteration with Non-FibonacciNumber should be false`() {
        assertFalse(checkIfFibonacciUsingIteration(14))
    }

    @Test
    fun `test checkIfFibonacciUsingHashSet should give correct results`() {
        assertTrue(checkIfFibonacciUsingHashSet(233))
        assertFalse(checkIfFibonacciUsingHashSet(4))
        assertTrue(checkIfFibonacciUsingHashSet(13))
        assertFalse(checkIfFibonacciUsingHashSet(2500))
    }
}
