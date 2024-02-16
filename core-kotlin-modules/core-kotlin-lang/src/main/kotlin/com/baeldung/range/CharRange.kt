package com.baeldung.range

fun main() {

    for (ch in 'a'..'f') {
        print(ch)
    }
    println()

    for (ch in 'f' downTo 'a') {
        print(ch)
    }
}