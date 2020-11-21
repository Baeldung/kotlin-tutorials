package com.baeldung.receiver

fun main() {
    "Baeldung".applyThenReturn { n -> println(n.toUpperCase()) }
    "Baeldung".apply { println(toUpperCase()) }
}

fun <T> T.applyThenReturn(f: (T) -> Unit): T {
    f(this)
    return this
}

fun <T> T.apply(f: T.() -> Unit): T {
    f() // or this.f()
    return this
}
