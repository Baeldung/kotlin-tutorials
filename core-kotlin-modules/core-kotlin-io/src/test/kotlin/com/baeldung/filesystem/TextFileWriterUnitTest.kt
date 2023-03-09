package com.baeldung.filesystem

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

internal class TextFileWriterUnitTest {

    private val fileName = "src/test/resources/Kotlin.txt"

    private val fileContent = "Kotlin\nConcise, Safe, Interoperable, Tool-friendly"

    private val textFileWriter = TextFileWriter()

    @Test
    fun whenCreatedDirectly_thenCorrect() {
        textFileWriter.createFileDirectly(fileName)

        assertEquals(0, File(fileName).length())
    }

    @Test
    fun whenWrittenDirectly_thenCorrect() {
        textFileWriter.writeFileDirectly(fileName, fileContent)

        assertEquals(fileContent, File(fileName).readText())
    }

    @Test
    fun whenAppendedDirectly_thenCorrect() {
        textFileWriter.appendFileDirectly(fileName, fileContent)

        assertEquals(fileContent, File(fileName).readText())
    }

    @Test
    fun whenWrittenWithFileOutputStream_thenCorrect() {
        textFileWriter.writeFileUsingFileOutputStream(fileName, fileContent)

        assertEquals(fileContent, File(fileName).readText())
    }

    @Test
    fun whenWrittenWithBufferedWriter_thenCorrect() {
        textFileWriter.writeFileUsingBufferedWriter(fileName, fileContent)

        assertEquals(fileContent, File(fileName).readText())
    }

    @Test
    fun whenWrittenWithPrintWriter_thenCorrect() {
        textFileWriter.writeFileUsingPrintWriter(fileName, fileContent)

        assertEquals(fileContent, File(fileName).readText())
    }

}