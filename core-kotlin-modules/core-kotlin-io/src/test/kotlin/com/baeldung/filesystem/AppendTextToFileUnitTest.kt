package com.baeldung.filesystem

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import kotlin.test.assertEquals

class AppendTextToFileUnitTest {
    @TempDir
    lateinit var tempPath: Path

    private val existingLines = listOf("line 1", "line 2", "line 3")
    private val expectedContent = (existingLines + "a new line").joinToString(separator = "\n") { it }

    lateinit var myFile: File

    @BeforeEach
    fun initFile() {
        val myFilePath = tempPath.resolve("myFile.txt")
        Files.write(myFilePath, existingLines)
        myFile = myFilePath.toFile()
    }

    @Test
    fun `when using appendText(), then get expected result`() {
        myFile.appendText("a new line")
        assertEquals(expectedContent, myFile.readText())
    }

    @Test
    fun `when using appendBytes(), then get expected result`() {
        myFile.appendBytes("a new line".toByteArray())
        assertEquals(expectedContent, myFile.readText())
    }
}