package com.baeldung.range

fun main() {
    val r = 1..10

    //Apply filter
    val f = r.filter { it % 2 == 0 }
    println(f)

    //Map
    val m = r.map { it * it }
    println(m)

    //Reduce
    val rdc = r.reduce { a, b -> a + b }
    println(rdc)

}