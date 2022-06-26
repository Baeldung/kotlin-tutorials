package com.baeldung.solid

class A
class B
class C

fun f(a: A): B = TODO()
fun g(b: B): C = TODO()
fun h(a: A): C = g(f(a))

fun interface G {
    fun invoke(b: B): C
}

fun H(a: A, f: (A) -> B, g: G) = g(f(a))