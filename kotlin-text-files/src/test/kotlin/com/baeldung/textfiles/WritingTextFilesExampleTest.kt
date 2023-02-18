package com.baeldung.textfiles

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class WritingTextFilesExampleTest {

    companion object {
        const val expectedTextInOutputFile = "1\n2\n3\n4\n5\n"
        const val outputFileName = "test.out.txt"
    }

    @AfterEach
    fun cleanup() {
        val outputFile = File(outputFileName)
        if (outputFile.exists()) {
            outputFile.delete()
        }
    }

    @Test
    fun shouldWriteNumberUpToFive_UsingWriteText() {
        WritingTextFilesExample.writeToFileUsingWriteText(
            filename = outputFileName, n = 5
        )

        assertEquals(expectedTextInOutputFile, File(outputFileName).readText())
    }

    @Test
    fun shouldWriteNumberUpToFive_UsingPrintWriter() {
        WritingTextFilesExample.writeToFileUsingPrintWriter(
            filename = outputFileName, n = 5
        )

        assertEquals(expectedTextInOutputFile, File(outputFileName).readText())
    }

    @Test
    fun shouldWriteNumberUpToFive_UsingBufferedWriter() {
        WritingTextFilesExample.writeToFileUsingBufferedWriter(
            filename = outputFileName, n = 5
        )

        assertEquals(expectedTextInOutputFile, File(outputFileName).readText())
    }

    @Test
    fun shouldWriteNumberUpToFive_WithoutExtensionFunctions() {
        WritingTextFilesExample.writeToFileLikeInJava(
            filename = outputFileName, n = 5
        )

        assertEquals(expectedTextInOutputFile, File(outputFileName).readText())
    }
}