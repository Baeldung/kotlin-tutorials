package com.baeldung.backingfield

fun main(args: Array<String>) {
    val fileName = "C:/Users/USER/Documents/Data2.txt"
    val fileContent = "This is a test text"
    val appendText = "this is an appended text"

    File(fileName).bufferedWriter().use { out -> out.write(fileContent) }

    File(fileName).appendBytes(appendText.toByteArray())
}