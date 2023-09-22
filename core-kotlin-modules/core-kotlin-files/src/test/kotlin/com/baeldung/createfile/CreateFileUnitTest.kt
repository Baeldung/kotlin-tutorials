package com.baeldung.createfile

import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import java.io.File
import java.io.FileWriter
import java.io.PrintWriter

class CreateFileUnitTest {

    @Test
    fun `create file using JavaIO method`() {
        val filePath = "albert.txt"
        val content = "Hello, Albert!"
        createTextFileUsingJavaIO(filePath, content)

        val file = File(filePath)
        assertTrue(file.exists())
        assertEquals(content, file.readText())
    }

    @Test
    fun `create file using PrintWriter method`() {
        val filePath = "ache.txt"
        val content = "Hello, Ache!"
        createTextFileUsingPrintWriter(filePath, content)

        val file = File(filePath)
        assertTrue(file.exists())
        assertEquals("Hello, Ache!\n", file.readText())
    }

    @Test
    fun `create file using File class`() {
        val filePath = "aa.txt"
        val content = "Hello, Albert Ache!"
        createTextFileUsingFile(filePath, content)

        val file = File(filePath)
        assertTrue(file.exists())
        assertEquals(content, file.readText())
    }
}
fun createTextFileUsingJavaIO(filePath: String, content: String) {
    val fileWriter = FileWriter(filePath)
    fileWriter.write(content)
    fileWriter.close()
}

fun createTextFileUsingPrintWriter(filePath: String, content: String) {
    val file = File(filePath)
    val printWriter = PrintWriter(file)
    printWriter.println(content)
    printWriter.close()
}

fun createTextFileUsingFile(filePath: String, content: String) {
    val file = File(filePath)
    file.createNewFile()
    file.writeText(content)
}