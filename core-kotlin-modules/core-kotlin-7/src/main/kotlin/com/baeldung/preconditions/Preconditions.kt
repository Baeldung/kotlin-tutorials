package com.baeldung.preconditions

fun printPositiveNumber(num: Int) {
    require(num > 0) { "Number must be positive" }
    println(num)
}

fun printListSize(list: List<Int>, size: Int) {
    check(list.size == size) { "List must contain $size elements" }
    println(list)
}

fun divide(a: Int, b: Int): Int {
    if (b == 0) {
        error("Cannot divide by zero")
    }
    return a / b
}
