package com.baeldung.elvisoperator

fun main(args: Array<String>) {

    var word: String? = "Elvis"
    val length: Int = if (word != null) word.length else -1

    print(length)
    println()

    val notNullStringLength = word?.length ?: -1
    print(notNullStringLength)
    println()

    val lengthOrThrow = word?.length ?: throw IllegalArgumentException("empty string not allowed")

    print(lengthOrThrow)
    println()

    word = null
    val nullWordLength = word?.length ?: -1

    print(nullWordLength)
    println()

    val a = null
    val b = null

    val result = a ?: b ?: "a and b are null"

    print(result)
    println()
}

