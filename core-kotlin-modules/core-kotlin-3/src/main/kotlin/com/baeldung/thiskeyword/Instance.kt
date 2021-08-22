package com.baeldung.thiskeyword

class Foo {
    var count = 0

    fun incrementCount() {
        incrementFoo(this)
    }
}

private fun incrementFoo(foo: Foo) {
    foo.count += 2
}

fun main() {
    val foo = Foo()
    foo.incrementCount()

    println("Final count = ${foo.count}")
}
