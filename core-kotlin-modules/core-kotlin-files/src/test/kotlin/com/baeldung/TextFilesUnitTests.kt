package com.baeldung

import org.junit.Test
import java.io.File
import kotlin.test.assertTrue
import org.junit.Assert.assertEquals

class TextFilesUnitTests {

    @Test
    fun whenCreateTextFile_thenFileShouldExist() {
        val fileName = "data.txt"
        val file = File(fileName)
        val isNewFileCreated: Boolean = file.createNewFile()
        assertTrue(isNewFileCreated, "New file should be created.")
        assertTrue(file.exists(), "File should exist after creation.")
        assertTrue(file.isFile, "File should be a regular file.")
        file.delete()
    }

    @Test
    fun whenWriteTextFile_thenFileShouldMatchContentWritten() {
        val myFileName = "example.txt"
        val file = File(myFileName)
        file.writeText("Hello, World!")
        assertTrue(file.exists(), "File should exist after writing text.")
        assertEquals("Hello, World!", file.readText(), "Hello, World!")
        file.delete()
    }


    @Test
    fun whenWriteWithByteArray_thenFileShouldReadContentWritten() {
        val fileName = "file.txt"
        val content = "This is my content"
        val bytes = content.toByteArray()
        val file = File(fileName)
        file.writeBytes(bytes)
        assertEquals(content, file.readText(), "This is my content")
        file.delete()
    }

}