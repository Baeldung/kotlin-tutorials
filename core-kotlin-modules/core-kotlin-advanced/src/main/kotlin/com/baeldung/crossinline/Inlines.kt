package com.baeldung.crossinline

inline fun executeAll(action1: () -> Unit, noinline action2: () -> Unit) {
    action1()
    action2()
}

inline fun foo(crossinline f: () -> Unit) {
    bar { f() }
}

fun bar(f: () -> Unit) {
    f()
}

fun main() {
    executeAll({ print("Hello") }, { print(" World") })
}