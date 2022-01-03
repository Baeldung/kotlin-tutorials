package com.baeldung.array

val dice = arrayOf(1, 2, 3, 4, 5, 6)

fun main(args: Array<String>) {
    // Traversal using for loop
    for (faceValue in dice) {
        println(faceValue)
    }

    // Traversal using iterator
    val iterator = dice.iterator()
    while (iterator.hasNext()) {
        val faceValue = iterator.next()
        println(faceValue)
    }

    // Traversal using forEach
    dice.forEach { faceValue ->
        println(faceValue)
    }

    // Traversal using forEachIndexed
    dice.forEachIndexed { index, faceValue ->
        println("Value at $index position is $faceValue")
    }
}