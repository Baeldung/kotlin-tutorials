package com.baeldung.filesystem

import com.baeldung.filesystem.FileCreator
import com.baeldung.filesystem.FileCreator.Companion.FILE_CREATION_EXCEPTION_MESSAGE
import com.baeldung.filesystem.FileCreator.Companion.FILE_CREATION_SUCCESS_MESSAGE
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.PrintStream

internal class FileCreatorUnitTest {
    private val outContent: ByteArrayOutputStream = ByteArrayOutputStream()
    private val fileName = "/Users/username/Desktop/fileName.txt"

    private val fileCreator = FileCreator()

    @Before
    fun setup() {
        System.setOut(PrintStream(outContent));
    }

    @Test
    fun `createFileUsingFileApi when filename is correct then creates file successfully`(){
        fileCreator.createFileUsingFileApi(fileName)
        assertEquals(FILE_CREATION_SUCCESS_MESSAGE, outContent.toString().trim())
    }

    @Test
    fun `createFileUsingFileApi when filename is empty then throws exception`(){
        fileCreator.createFileUsingFileApi("")
        assertTrue(FILE_CREATION_EXCEPTION_MESSAGE in outContent.toString().trim())
    }

    @Test
    fun `createFileUsingFilesApi when filename is correct then creates file successfully`(){
        fileCreator.createFileUsingFilesApi(fileName)
        assertEquals(FILE_CREATION_SUCCESS_MESSAGE, outContent.toString().trim())
    }

    @Test
    fun `createFileUsingFilesApi when filename is empty then throws exception`(){
        fileCreator.createFileUsingFilesApi("")
        assertTrue(FILE_CREATION_EXCEPTION_MESSAGE in outContent.toString().trim())
    }

    @Test
    fun `createFileUsingFileOutputStream when filename is correct then creates file successfully`(){
        fileCreator.createFileUsingFileOutputStream(fileName)
        assertEquals(FILE_CREATION_SUCCESS_MESSAGE, outContent.toString().trim())
    }

    @Test
    fun `createFileUsingFileOutputStream when filename is empty then throws exception`(){
        fileCreator.createFileUsingFileOutputStream("")
        assertTrue(FILE_CREATION_EXCEPTION_MESSAGE in outContent.toString().trim())
    }

    @Test
    fun `createFileUsingGoogleGuava when filename is correct then creates file successfully`(){
        fileCreator.createFileUsingGoogleGuava(fileName)
        assertEquals(FILE_CREATION_SUCCESS_MESSAGE, outContent.toString().trim())
    }

    @Test
    fun `createFileUsingGoogleGuava when filename is empty then throws exception`(){
        fileCreator.createFileUsingFileOutputStream("")
        assertTrue(FILE_CREATION_EXCEPTION_MESSAGE in outContent.toString().trim())
    }

    @After
    fun cleanup() {
        val targetFile = File(fileName)
        targetFile.delete()
    }

}