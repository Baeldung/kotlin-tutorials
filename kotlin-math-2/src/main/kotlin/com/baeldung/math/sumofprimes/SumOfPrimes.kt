package com.baeldung.math.sumofprimes


fun checkPrimeUsingBruteForce(number: Int): Boolean {
    if (number <= 1) return false
    for (i in 2 until number) {
        if (number % i == 0) {
            return false
        }
    }
    return true
}

fun canBeExpressedAsSumOfTwoPrimesUsingBruteForceApproach(n: Int): Boolean {
    for (i in 2..n / 2) {
        if (checkPrimeUsingBruteForce(i) && checkPrimeUsingBruteForce(n - i)) {
            return true
        }
    }
    return false
}

fun sieveOfEratosthenes(n: Int): BooleanArray {
    val primes = BooleanArray(n + 1) { true }
    primes[0] = false
    primes[1] = false
    for (p in 2..n) {
        if (primes[p]) {
            for (i in p * p..n step p) {
                primes[i] = false
            }
        }
    }
    return primes
}

fun canBeExpressedAsSumOfTwoPrimesUsingSieveOfEratosthenes(n: Int): Boolean {
    if (n < 2) return false
    val primes = sieveOfEratosthenes(n)
    for (i in 2 until n) {
        if (primes[i] && primes[n - i]) {
            return true
        }
    }
    return false
}
