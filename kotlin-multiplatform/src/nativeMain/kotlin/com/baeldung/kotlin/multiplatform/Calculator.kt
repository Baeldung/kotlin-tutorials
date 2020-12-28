package com.baeldung.kotlin.multiplatform

fun main() {
    println("Enter a Number:")
    val number = readLine()!!.toInt()
    println("Square of the Input: ${number * number}")
}