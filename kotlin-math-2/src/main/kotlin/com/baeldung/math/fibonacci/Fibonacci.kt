package com.baeldung.math.fibonacci

fun fibonacciUsingRecursion(num: Int): Int {
    return if (num <= 1) {
        num
    } else {
        fibonacciUsingRecursion(num - 1) + fibonacciUsingRecursion(num - 2)
    }
}

fun fibonacciUsingIteration(num: Int): Int {
    var a = 0
    var b = 1
    var tmp: Int
    for (i in 2..num) {
        tmp = a + b
        a = b
        b = tmp
    }
    return b
}

tailrec fun fibonacciUsingTailRecursion(num: Int, a: Int = 0, b: Int = 1): Int {
    return if (num == 0) a else fibonacciUsingTailRecursion(num - 1, b, a + b)
}

fun fibonacciUsingSequence(num: Int): Int {
    val fibonacciSequence = generateSequence(Pair(1, 1)) { Pair(it.second, it.first + it.second) }.map { it.first }
    return fibonacciSequence.take(num).last()
}