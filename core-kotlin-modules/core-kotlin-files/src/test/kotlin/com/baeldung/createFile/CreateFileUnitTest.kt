package com.baeldung.createFile

import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.jupiter.api.Test
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.PrintWriter

class createFileUnitTest {

    @Test
    fun `Create a text file using File class`() {
        val file = usingFileClass("test.txt")

        assertTrue(file.exists())
        assertTrue(file.isFile())
    }

    @Test
    fun `Create text file using PrintWriter`() {
        val fileName = "example.txt"
        val content = "This is a test content"
        usingPrintWriter(fileName, content)
        val file = File(fileName)

        assertTrue(file.exists())
        assertTrue(file.isFile())
        assertEquals("This is a test content\n", file.readText())
    }

    @Test
    fun `Create text file using BufferedWriter`() {
        val fileName = "example.txt"
        val content = "This is a test content"
        usingBufferedWriter(fileName, content)
        val file = File(fileName)

        assertTrue(file.exists())
        assertTrue(file.isFile())
    }

}
fun usingFileClass(filePath: String): File {
    val file = File(filePath)
    file.createNewFile()
    return file
}
fun usingPrintWriter(fileName: String, content: String) {
    PrintWriter(fileName).use { writer ->
        writer.println(content)
    }
}

fun usingBufferedWriter(fileName: String, content: String) {
    BufferedWriter(FileWriter(fileName)).use { writer ->
        writer.write(content)
    }
}