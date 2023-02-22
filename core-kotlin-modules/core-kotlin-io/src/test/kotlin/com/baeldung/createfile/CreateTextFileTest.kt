package com.baeldung.createfile

import org.apache.commons.io.FileUtils
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File
import java.io.FileOutputStream
import java.nio.file.Files
import kotlin.io.path.Path
import kotlin.io.path.createFile

class CreateTextFileTest {

    private val testTextFileName = "src/test/resources/my-text-file.txt"

    @AfterEach
    fun tearDown() {
        Files.deleteIfExists(Path(testTextFileName))
    }

    @Test
    fun givenUsingPath_whenCreateFile_thenFileExists() {
        Path(testTextFileName).createFile()

        Assertions.assertTrue(File(testTextFileName).exists())
    }

    @Test
    fun givenUsingNioFiles_whenCreateFile_thenFileExists() {
        Files.createFile(Path(testTextFileName))

        Assertions.assertTrue(File(testTextFileName).exists())
    }

    @Test
    fun givenUsingFile_whenWriteText_thenFileExists() {
        File(testTextFileName).writeText("")

        Assertions.assertTrue(File(testTextFileName).exists())
    }

    @Test
    fun givenUsingFile_whenWriteBytes_thenFileExists() {
        File(testTextFileName).writeBytes(ByteArray(0))

        Assertions.assertTrue(File(testTextFileName).exists())
    }

    @Test
    fun whenCreatingFileOutputStream_thenFileExists() {
        FileOutputStream(testTextFileName).use {
            Assertions.assertTrue(File(testTextFileName).exists())
        }
    }

    @Test
    fun givenApacheCommonsIo_whenFileUtilsTouch_thenFileExists() {
        FileUtils.touch(File(testTextFileName))

        Assertions.assertTrue(File(testTextFileName).exists())
    }
}