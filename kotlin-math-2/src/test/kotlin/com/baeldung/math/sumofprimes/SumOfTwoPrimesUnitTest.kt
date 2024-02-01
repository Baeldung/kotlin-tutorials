package com.baeldung.math.sumofprimes

import kotlin.test.assertTrue
import kotlin.test.assertFalse
import org.junit.jupiter.api.Test

class SumOfTwoPrimesUnitTest {

    private val sumOfTwoPrimes = arrayOf(10, 13, 25, 28)
    private val notSumOfTwoPrimes = arrayOf(11, 27, 51, 57)

    @Test
    fun `given number that can be expressed as sum of two primes, when check using brute force approach, then return true`() {
        for (n in sumOfTwoPrimes) {
            val result = canBeExpressedAsSumOfTwoPrimesUsingBruteForceApproach(n)
            assertTrue(result)
        }
    }

    @Test
    fun `given number that cannot be expressed as sum of two primes, when check using brute force approach, then return false`() {
        for (n in notSumOfTwoPrimes) {
            val result = canBeExpressedAsSumOfTwoPrimesUsingBruteForceApproach(n)
            assertFalse(result)
        }
    }

    @Test
    fun `given number that can be expressed as sum of two primes, when check using sieve of eratosthenes, then return true`() {
        for (n in sumOfTwoPrimes) {
            val result = canBeExpressedAsSumOfTwoPrimesUsingSieveOfEratosthenes(n)
            assertTrue(result)
        }
    }

    @Test
    fun `given number that cannot be expressed as sum of two primes, when check using sieve of eratosthenes, then return false`() {
        for (n in notSumOfTwoPrimes) {
            val result = canBeExpressedAsSumOfTwoPrimesUsingSieveOfEratosthenes(n)
            assertFalse(result)
        }
    }

}
