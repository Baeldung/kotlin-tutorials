package com.baeldung.filesystem

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

internal class TextToFileWriterUnitTest {

    private val fileName = "src/test/resources/TextToFileSample.txt"
    private val text = "Comparing FileOutputStream, PrintWriter and BufferedWriter"

    private val textToFileWriter = TextToFileWriter()

    @AfterEach
    fun setUp() {
        try {
            File(fileName).delete()
        } catch (e: Exception) {
            // Ignore if not allowed
        }
    }

    @Test
    fun whenCreatingTextFileUsingFileOutPutStream_thenItIsCreated() {
        // when
        textToFileWriter.createTextFileUsingFileOutputStream(fileName, text)

        // then
        File(fileName).let { file ->
            assert(file.exists())
            assertEquals(text, file.readText())
        }
    }

    @Test
    fun whenCreatingTextFileUsingPrintWriter_thenItIsCreated() {
        // when
        textToFileWriter.createTextFileUsingPrintWriter(fileName, text)

        // then
        File(fileName).let { file ->
            assert(file.exists())
            assertEquals(text, file.readText())
        }
    }

    @Test
    fun whenCreatingTextFileUsingBufferedWriter_thenItIsCreated() {
        // when
        textToFileWriter.createTextFileUsingBufferedWriter(fileName, text)

        // then
        File(fileName).let { file ->
            assert(file.exists())
            assertEquals(text, file.readText())
        }
    }
}