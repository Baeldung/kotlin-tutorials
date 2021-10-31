package com.baeldung.array

import kotlin.random.Random

fun initArray(): Array<Int> {
    return arrayOf(3, 2, 50, 15, 10, 1)
}

fun printArray(array: Array<Int>) {
    array.forEach { print("$it ") }
    println()
}

fun main(args: Array<String>) {
    var numbers = initArray()
    printArray(numbers)

    // Complete Array Reverse
    numbers.reverse()
    printArray(numbers)

    // Partial Array Reverse
    numbers = initArray()
    numbers.reverse(4, 6)
    printArray(numbers)

    // Complete Array Sort
    numbers = initArray()
    numbers.sort()
    printArray(numbers)

    // Partial Array Sort After an Index
    numbers = initArray()
    numbers.sort(4)
    printArray(numbers)

    // Partial Array Sort Between two indices
    numbers = initArray()
    numbers.sort(0, 2)
    printArray(numbers)

    // Shuffle
    numbers = initArray()
    numbers.shuffle()
    printArray(numbers)

    numbers = initArray()
    numbers.shuffle(Random(2))
    printArray(numbers)

    numbers = initArray()

    print(numbers.associate { Pair(it, 2 * it) })
    print(numbers.associateBy { 2 * it })
    print(numbers.associateWith { 2 * it })
    val temp = mutableMapOf<Int, Int>()
    println()
    println(numbers.associateByTo(temp, { 2 * it }, { 3 * it }))

    println(temp)
    numbers.associateTo(temp) { it to 2 * it }
}