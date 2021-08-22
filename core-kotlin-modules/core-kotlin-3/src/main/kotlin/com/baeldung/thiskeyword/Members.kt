package com.baeldung.thiskeyword

class Foo {
    var count = 0

    fun incrementCount() {
        this.count += 2
    }
}

fun main() {
    val foo = Foo()
    foo.incrementCount()
    println("Final count = ${foo.count}")
}
