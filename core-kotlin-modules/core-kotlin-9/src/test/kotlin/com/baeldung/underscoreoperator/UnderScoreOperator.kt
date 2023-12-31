package com.baeldung.underscoreoperator

// Before Kotlin 1.7
class Box<T>(value: T) {
    // Some class implementation
}
fun main1() {
    val intBox: Box<Int> = Box(42)
    val stringBox: Box<Unit> = Box(Unit) // Using Unit as a placeholder
}


fun main(args: Array<String>) {
    val list = listOf("hi")
    val list2 = list.mapIndexed { _, item -> item }
    println(list2)
    val (_, second) = "ignored" to "hello"
    println(second)
}

// Higher-order function with a generic parameter
inline fun <reified T> printElementInfo(element: T) {
    println("Type: ${T::class.simpleName}, Value: $element")
}

fun main() {
    printElementInfo<_>(42)
    printElementInfo<_>("Hello")
}
