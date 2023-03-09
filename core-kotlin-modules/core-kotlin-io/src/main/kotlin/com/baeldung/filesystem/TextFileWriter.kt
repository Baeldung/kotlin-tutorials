package com.baeldung.filesystem

import java.io.File
import java.io.FileOutputStream
import java.io.BufferedWriter
import java.io.FileWriter
import java.io.PrintWriter

class TextFileWriter {

    fun createFileDirectly(fileName: String) {
        val file = File(fileName)
        file.createNewFile()
    }

    fun writeFileDirectly(fileName: String, fileContent: String) {
        val file = File(fileName)
        file.writeText(fileContent)
    }

    fun appendFileDirectly(fileName: String, fileContent: String) {
        val file = File(fileName)
        file.appendText(fileContent)
    }

    fun writeFileUsingFileOutputStream(fileName: String, fileContent: String) {
        val outputStream = FileOutputStream(fileName)
        outputStream.write(fileContent.toByteArray())
        outputStream.close()
    }

    fun writeFileUsingBufferedWriter(fileName: String, fileContent: String) {
        val writer = BufferedWriter(FileWriter(File(fileName)))
        writer.write(fileContent)
        writer.close()
    }

    fun writeFileUsingPrintWriter(fileName: String, fileContent: String)  {
        val writer = PrintWriter(File(fileName))
        writer.print(fileContent)
        writer.close()
    }

}