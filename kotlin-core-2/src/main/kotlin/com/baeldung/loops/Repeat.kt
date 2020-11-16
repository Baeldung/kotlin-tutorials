package com.baeldung.loops

fun main() {

    repeat(2) {
        println("Hello World")
    }

    repeat(2) { index ->
        println("Iteration ${index + 1}: Hello World")
    }
}