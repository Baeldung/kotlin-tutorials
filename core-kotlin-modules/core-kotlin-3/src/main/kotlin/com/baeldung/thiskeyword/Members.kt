package com.baeldung.thiskeyword

class Counter {
    var count = 0

    fun incrementCount() {
        this.count += 2
    }
}

fun main() {
    val counter = Counter()
    counter.incrementCount()
    println("Final count = ${counter.count}")
}
