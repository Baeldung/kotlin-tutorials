package com.baeldung.filecreation

import com.baeldung.files.creation.FileCreator
import com.baeldung.filesystem.FileReader
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class FileCreatorUnitTest {

    private val fileName = "src/test/resources/super.txt"
    private val fileCreator = FileCreator()
    private val fileReader = FileReader()

    @Test
    fun `create file using createNewFile Method with success`(){
        val isNewFileCreated = fileCreator.createFileWithCreateNewFileMethod(fileName + System.currentTimeMillis())
        assertEquals(true, isNewFileCreated)
    }

    @Test
    fun `create file using createNewFile Method with failure`(){
        val isNewFileCreated = fileCreator.createFileWithCreateNewFileMethod(fileName)
        assertEquals(false, isNewFileCreated)
    }

    @Test
    fun `create file using writeText Method with success`(){
        fileCreator.createFileWithWriteTextMethod(fileName, "New file")
        val lines = fileReader.readFileAsLinesUsingUseLines(fileName)
        assertTrue(lines.contains("New file"))
    }

    @Test
    fun `create file using writeText Method with overwrite`(){
        fileCreator.createFileWithWriteTextMethod(fileName, "")
        val lines = fileReader.readFileAsLinesUsingUseLines(fileName)
        assertFalse(lines.contains("New file"))
    }

    @Test
    fun `create file with writeByte Method with success`(){
        fileCreator.createFileWithWriteByteMethod(fileName, "New file")
        val lines = fileReader.readFileAsLinesUsingUseLines(fileName)
        assertTrue(lines.contains("New file"))
    }

    @Test
    fun `create file with writeText Method with success`(){
        fileCreator.createFileWithWriteTextMethod(fileName, "")
        val lines = fileReader.readFileAsLinesUsingUseLines(fileName)
        assertFalse(lines.contains("New file"))
    }
}