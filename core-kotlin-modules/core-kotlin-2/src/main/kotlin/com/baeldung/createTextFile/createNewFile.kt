package com.baeldung.backingfield

fun main(args: Array<String>) {
    val fileName = "C:/Users/USER/Documents/Data.txt"
    val file = File(fileName)
    val isNewFileCreated: Boolean = file.createNewFile()
    if (isNewFileCreated) {
        println("$fileName is created successfully.")
    } else {
        println("$fileName already exists.")
    }
}