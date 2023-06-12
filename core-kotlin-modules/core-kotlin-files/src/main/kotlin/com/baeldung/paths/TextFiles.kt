package com.baeldung.paths

import java.io.File

object TextFiles {

    fun createFile() {
        val fileName = "data.txt"
        val file = File(fileName)
        val isNewFileCreated :Boolean = file.createNewFile()
        if(isNewFileCreated){
            println("$fileName is created successfully.")
        } else{
            println("$fileName already exists.")
        }
        // try creating a file that already exists
        val isFileCreated :Boolean = file.createNewFile()
        if(isFileCreated){
            println("$fileName is created successfully.")
        } else{
            println("$fileName already exists.")
        }
    }

    fun writeText() {
        val myFileName = "example.txt"
        val file = File(myFileName)
        file.writeText("")
    }

    fun writeBytes() {
        val fileName = "file.txt"
        val content = "This is my content"
        val bytes = content.toByteArray()
        File(fileName).writeBytes(bytes)
        println("Text file created: $fileName")
    }
}