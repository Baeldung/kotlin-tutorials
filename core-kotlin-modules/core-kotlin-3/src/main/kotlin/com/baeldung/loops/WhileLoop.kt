package com.baeldung.loops

fun main() {
    var counter = 0
    while (counter < 5) {
        println("while loop counter: " + counter++)
    }

    while(false) {
        println("This will never be printed")
    }
}