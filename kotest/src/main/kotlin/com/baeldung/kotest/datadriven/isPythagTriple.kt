package com.baeldung.kotest.datadriven

fun isPythagTriple(a: Int, b: Int, c: Int): Boolean {
    if(a <= 0 || b <= 0 || c <= 0) return false
    return a * a + b * b == c * c
}
