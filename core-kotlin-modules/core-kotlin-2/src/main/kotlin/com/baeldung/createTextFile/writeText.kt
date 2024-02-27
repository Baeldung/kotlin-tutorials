package com.baeldung.backingfield

fun main(args: Array<String>) {
    val fileName = "C:/Users/USER/Documents/Data.txt"
    val file = File(fileName)
    file.writeText("This is some text")
}