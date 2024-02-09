package com.baeldung.math.prime

import java.math.BigInteger
import kotlin.math.sqrt

fun isPrimeUsingUsingIteration(num: Int): Boolean {
    if (num < 2) return false
    val sqrt = sqrt(num.toDouble()).toInt()
    for (i in 2..sqrt) {
        if (num % i == 0)
            return false
    }
    return true
}

fun isPrimeUsingFunctionalProgram(num: Int): Boolean {
    if (num < 2) return false
    val sqrt = sqrt(num.toDouble()).toInt()
    return (2..sqrt).none { num % it == 0 }
}

fun isPrimeBigInt(num: Int): Boolean {
    if (num < 2) return false
    return num.toBigInteger().isProbablePrime(100)
}