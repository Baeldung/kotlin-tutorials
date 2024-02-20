package com.baeldung.solid

class A
class B
class C

fun f(a: A): B {
    println("Input value of f is: $a")
    return B()
}
fun g(b: B): C {
    println("Input value of g is: $b")
    return C()
}
fun h(a: A): C = g(f(a))

fun interface G {
    fun invoke(b: B): C
}

fun H(a: A, f: (A) -> B) = g(f(a))