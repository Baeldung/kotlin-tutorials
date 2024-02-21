package com.baeldung.underscoreoperator
// Before Kotlin 1.7
class Box<T>(value: T) {
    // Some class implementation
    var a = value
}
fun main() {
    val intBox: Box<Int> = Box(42)
    println("Int box is: $intBox")
    val anyBox: Box<Any> = Box("Some value")
    println("Any box is: $anyBox")
}
fun main1() {
    val (_, second) = "ignored" to "hello"
    println(second)
}
fun mainTwo() {
    val list = listOf("hi")
    val list2 = list.mapIndexed { _, item -> item }
    println(list2)
}
fun <T> printElementInfo(element: T) {
    println("Element: $element")
}
fun mainForHOF() {
    printElementInfo<_>(42)
    printElementInfo<_>("Hello")
}