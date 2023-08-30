package com.baeldung.math.checkfibonacci

var currentLimit = 0
var currentFibonacciHashSet: HashSet<Int> = hashSetOf(0, 1)
fun checkIfFibonacciUsingIteration(n: Int): Boolean {
    var first = 0
    var second = 1
    while (first < n) {
        val temp = first
        first = second
        second = temp + second
    }
    return n == first
}

fun isPerfectSquare(x: Int): Boolean {
    val s = Math.sqrt(x.toDouble()).toInt()
    return s * s == x
}

fun checkIfFibonacciUsingPerfectSquareProperty(n: Int): Boolean {
    return isPerfectSquare(5 * n * n + 4) || isPerfectSquare(5 * n * n - 4)
}

fun getNthFibonacci(n: Int, cache: HashMap<Int, Int>): Int {
    if (n == 0) return 0
    if (n == 1) return 1

    cache[n]?.let {
        return it
    }
    val result = getNthFibonacci(n - 1, cache) + getNthFibonacci(n - 2, cache)
    cache[n] = result
    return result
}

fun checkIfFibonacciUsingRecursion(num: Int): Boolean {
    var n = 0
    var cache: HashMap<Int, Int> = HashMap<Int, Int>()
    while (getNthFibonacci(n, cache) <= num) {
        if (getNthFibonacci(n, cache) == num) return true
        n++
    }
    return false
}

fun generateFibonacciNumberHashSet(limit: Int): HashSet<Int> {
    val fibonacciNumberSet = HashSet<Int>()
    var first = 0
    var second = 1
    while (first <= limit) {
        fibonacciNumberSet.add(first)
        val temp = first
        first = second
        second = temp + second
    }
    return fibonacciNumberSet
}

fun checkIfFibonacciUsingHashSet(n: Int): Boolean {
    if (n > currentLimit) {
        currentFibonacciHashSet.addAll(generateFibonacciNumberHashSet(n))
        currentLimit = n
    }
    return currentFibonacciHashSet.contains(n)
}

