package com.baeldung.math.sum

fun sumUsingForLoop(n: Int): Int {
    var sum = 0
    for (i in 1..n) {
        sum += i
    }
    return sum
}

fun sumUsingWhileLoop(n: Int): Int {
    var sum = 0
    var i = 1
    while (i <= n) {
        sum += i
        i++
    }
    return sum
}

fun sumUsingArithmeticProgressionFormula(n: Int): Int {
    return (n * (n + 1)) / 2
}

fun sumUsingRecursion(n: Int): Int {
    return if (n <= 1) {
        n
    } else {
        n + sumUsingRecursion(n - 1)
    }
}

fun sumUsingRangeAndSum(n: Int): Int {
    return (1..n).sum()
}

fun sumUsingRangeAndReduce(n: Int): Int {
    return (1..n).reduce { acc, num -> acc + num }
}

fun sumUsingRangeAndSumBy(n: Int): Int {
    return (1..n).sumBy { it }
}

fun sumUsingRangeAndFold(n: Int): Int {
    return (1..n).fold(0) { acc, num -> acc + num }
}

fun sumUsingSequence(n: Int): Int {
    return generateSequence(1) { it + 1 }
        .take(n)
        .sum()
}
