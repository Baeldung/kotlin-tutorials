package com.baeldung.math.average

fun averageUsingForLoop(numbers: List<Int>): Double {
    var sum = 0.0
    for (num in numbers) {
        sum += num
    }
    val average = sum / numbers.size
    return average
}

fun averageUsingForEachLoop(numbers: List<Int>): Double {
    var sum = 0.0
    numbers.forEach { sum += it }
    val average = sum / numbers.size
    return average
}

fun averageUsingFold(numbers: List<Int>): Double {
    val sum = numbers.fold(0.0) { acc, num -> acc + num }
    val average = sum / numbers.size
    return average
}

fun averageUsingReduce(numbers: List<Int>): Double {
    val sum = numbers.reduce { acc, num -> acc + num }
    val average = sum.toDouble() / numbers.size
    return average
}


fun averageUsingSumByDouble(numbers: List<Int>): Double {
    val sum = numbers.sumOf { it.toDouble() }
    val average = sum / numbers.size
    return average
}


fun averageUsingSequence(numbers: List<Int>): Double {
    val sum = numbers.asSequence().map { it.toDouble() }.sum()
    val average = sum / numbers.size
    return average
}

fun averageUsingMapReduce(numbers: List<Int>): Double {
    val sum = numbers.map { it.toDouble() }.reduce { acc, num -> acc + num }
    val average = sum / numbers.size
    return average
}