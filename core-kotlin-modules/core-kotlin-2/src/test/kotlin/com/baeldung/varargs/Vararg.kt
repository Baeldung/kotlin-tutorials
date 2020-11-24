package com.baeldung.varargs

fun sum(vararg xs: Int): Int = xs.sum()

fun <T> printAll(vararg ts: T) {
    ts.forEach { println(it) } // Array<T>
}

fun printStrings(vararg vs: String) {
    vs.forEach { println(it) } // Array<String>
}

fun createUser(vararg roles: String, username: String, age: Int) {}

fun main() {
    createUser("admin", "user", username = "me", age = 42)
    val numbers = intArrayOf(1, 2)
    sum(*numbers)
}
