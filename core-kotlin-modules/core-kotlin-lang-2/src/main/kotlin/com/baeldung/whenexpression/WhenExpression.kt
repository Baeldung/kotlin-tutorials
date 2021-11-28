package com.baeldung.whenexpression

fun printNumberParity(number: Int) {
    when (number % 2) {
        0 -> println("$number is even")
        1 -> println("$number is odd")
    }
}

fun getMessageNumberParity(number: Int): String {
    val msg = when (number % 2) {
        0 -> "$number is even"
        1 -> "$number is odd"
        else -> "can't decide parity for $number"
    }
    return msg
}

fun getMessageNumberParityWithDebugging(number: Int): String {
    val msg = when (number % 2) {
        0 -> {
            println("debug: executing the branch for expression value = 0")
            "$number is even"
        }
        1 -> {
            println("debug: executing the branch for expression value = 1")
            "$number is odd"
        }
        else -> {
            println("debug: executing the else branch")
            "can't decide parity for $number"
        }
    }
    return msg
}

fun main() {
    printNumberParity(3)
    printNumberParity(4)

    println(getMessageNumberParity(3))
    println(getMessageNumberParity(4))

    println(getMessageNumberParityWithDebugging(3))
    println(getMessageNumberParityWithDebugging(4))
}