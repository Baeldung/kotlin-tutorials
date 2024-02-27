package com.baeldung.backingfield

fun main(args: Array<String>) {
    val fileName = "C:/Users/USER/Documents/Data2.txt"
    val file = File(fileName)
    val contentAsBytes: ByteArray = "This is some text".toByteArray()
    file.writeBytes(contentAsBytes)
}