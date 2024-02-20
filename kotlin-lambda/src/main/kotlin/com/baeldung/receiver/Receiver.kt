package com.baeldung.receiver

fun main() {
    "Baeldung".applyThenReturn { n -> println(n.uppercase()) }
    "Baeldung".apply { println(uppercase()) }
}

fun <T> T.applyThenReturn(f: (T) -> Unit): T {
    f(this)
    return this
}

fun <T> T.apply(f: T.() -> Unit): T {
    f() // or this.f()
    return this
}
