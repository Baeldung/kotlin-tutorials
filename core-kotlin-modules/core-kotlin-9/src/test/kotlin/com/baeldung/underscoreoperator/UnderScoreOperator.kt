package com.baeldung.underscoreoperator

// Before Kotlin 1.7
class Box<T>(value: T) {
    // Some class implementation
}
fun main() {
    val intBox: Box<Int> = Box(42)
    val anyBox: Box<Any> = Box("Some value")
}


fun main1(args: Array<String>) {
    val (_, second) = "ignored" to "hello"
    println(second)
}

fun mainTwo(args: Array<String>) {
    val list = listOf("hi")
    val list2 = list.mapIndexed { _, item -> item }
    println(list2)
}


inline fun <T> printElementInfo(element: T) {
}
fun mainForHOF() {
    printElementInfo<_>(42)
    printElementInfo<_>("Hello")
}




