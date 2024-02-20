package com.baeldung.deletefile

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.io.path.ExperimentalPathApi
import kotlin.io.path.deleteRecursively
import kotlin.io.path.exists
import kotlin.io.path.pathString

class DeleteFileUnitTest {

    @Test
    fun `given file path when deleteFile called then file is deleted`() {
        val tempFile = kotlin.io.path.createTempFile()
        assertTrue(tempFile.exists())

        deleteFile(tempFile.pathString)

        assertFalse(tempFile.exists())
    }

    @Test
    fun `given directory when deleteDirectory called then directory and its contents are deleted`() {
        val tempDir = kotlin.io.path.createTempDirectory()
        val tempFileInDir = File(tempDir.toFile(), "tempFile.txt").apply { createNewFile() }
        assertTrue(tempDir.exists())
        assertTrue(tempFileInDir.exists())

        deleteDirectory(tempDir.toFile())

        assertFalse(tempDir.exists())
        assertFalse(tempFileInDir.exists())
    }

    @Test
    fun `given a non-existent file should not throw`() {
        val file = File("imaginary-file.txt")

        assertDoesNotThrow {
            safeDeleteDirectory(file)
        }
    }

    @OptIn(ExperimentalPathApi::class)
    @Test
    fun `given directory when deleteDirectory called then directory and its contents are deleted recursively`() {
        val tempDir = kotlin.io.path.createTempDirectory()
        val innerTempDir = File(tempDir.toFile(), "innerTempDir").apply { mkdir() }
        val tempFileInDir = File(innerTempDir, "tempFile.txt").apply { createNewFile() }

        assertTrue(tempDir.exists())
        assertTrue(innerTempDir.exists())
        assertTrue(tempFileInDir.exists())

        tempDir.deleteRecursively()

        assertFalse(tempDir.exists())
        assertFalse(innerTempDir.exists())
        assertFalse(tempFileInDir.exists())
    }
}