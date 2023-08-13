package com.baeldung.filesystem

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class TextFileWriterUnitTest {

    private val pathname = "src/test/resources/Kotlin.out"

    private val text = "Kotlin\nConcise, Safe, Interoperable, Tool-friendly"

    private val textFileWriter = TextFileWriter()

    @BeforeEach
    fun setup() {
        File(pathname).apply {
            if (exists()) delete()
        }
    }

    @Test
    fun whenCreatingAnNewEmptyFile_thenCorrect() {
        textFileWriter.createNewFile(pathname)

        assertTrue(File(pathname).exists())
    }

    @Test
    fun whenWrittenAsBytes_thenCorrect() {
        val fileContentBytes = text.toByteArray()

        textFileWriter.writeFile(pathname, fileContentBytes)

        assertEquals(text, File(pathname).readText())
    }

    @Test
    fun whenWrittenAsString_thenCorrect() {
        textFileWriter.writeFile(pathname, text)

        assertEquals(text, File(pathname).readText())
    }


    @Test
    fun whenWrittenUsingPrinterWriter_thenCorrect() {
        textFileWriter.writeFileWithPrinterWriter(pathname, text)

        assertEquals(text, File(pathname).readText())
    }

    @Test
    fun whenWrittenUsingBufferedWriter_thenCorrect() {
        textFileWriter.writeFileWithBufferedWriter(pathname, text)

        assertEquals(text, File(pathname).readText())
    }

    @Test
    fun whenWrittenUsingBufferedWriterAppendLine_thenCorrect() {
        textFileWriter.writeFileWithBufferedWriterAppend(pathname, "Kotlin\n", "Concise, Safe, Interoperable, Tool-friendly")

        assertEquals(text, File(pathname).readText())
    }

}