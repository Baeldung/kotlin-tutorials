package com.baeldung.factorial

fun factorialRecursive(n: Int): Int {
    require(n >= 0) { "n must be positive" }
    return if (n <= 1) {
        1
    } else {
        n * factorialRecursive(n - 1)
    }
}

tailrec fun factorialTailRecursive(n: Int, accumulator: Int = 1): Int {
    return if (n <= 1) {
        accumulator
    } else {
        factorialTailRecursive(n - 1, n * accumulator)
    }
}

fun factorialIterative(n: Int): Int {
    var result = 1
    for (i in 1..n) {
        result *= i
    }
    return result
}

