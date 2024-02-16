package com.baeldung.math.prime

import org.junit.Test
import org.junit.jupiter.api.Assertions

class IsPrimeUnitTest {

    @Test
    fun `test isPrimeUsingUsingIteration for negative`() {
        Assertions.assertFalse(isPrimeUsingUsingIteration(-5))
    }

    @Test
    fun `test isPrimeUsingUsingIteration for zero`() {
        Assertions.assertFalse(isPrimeUsingUsingIteration(0))
    }

    @Test
    fun `test isPrimeUsingUsingIteration for a prime number`() {
        Assertions.assertTrue(isPrimeUsingUsingIteration(5))
    }

    @Test
    fun `test isPrimeUsingUsingIteration for a non-prime number`() {
        Assertions.assertFalse(isPrimeUsingUsingIteration(63))
    }

    @Test
    fun `test isPrimeUsingFunctionalProgram for a non-prime number`() {
        Assertions.assertFalse(isPrimeUsingFunctionalProgram(63))
    }

    @Test
    fun `test isPrimeUsingFunctionalProgram for a prime number`() {
        Assertions.assertTrue(isPrimeUsingFunctionalProgram(7))
    }

    @Test
    fun `test isPrimeUsingFunctionalProgram for zero`() {
        Assertions.assertFalse(isPrimeUsingFunctionalProgram(0))
    }

    @Test
    fun `test isPrimeUsingFunctionalProgram for negative`() {
        Assertions.assertFalse(isPrimeUsingFunctionalProgram(-5))
    }

    @Test
    fun `test isPrimeBigInt for a non-prime number`() {
        Assertions.assertFalse(isPrimeBigInt(63))
    }

    @Test
    fun `test isPrimeBigInt for a prime number`() {
        Assertions.assertTrue(isPrimeBigInt(7))
    }

    @Test
    fun `test isPrimeBigInt for zero`() {
        Assertions.assertFalse(isPrimeBigInt(0))
    }

    @Test
    fun `test isPrimeBigInt for negative`() {
        Assertions.assertFalse(isPrimeBigInt(-5))
    }

}