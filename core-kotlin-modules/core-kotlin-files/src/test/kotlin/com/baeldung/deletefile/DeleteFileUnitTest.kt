package com.baeldung.deletefile

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.io.File

class DeleteFileUnitTest {

    @Test
    fun `given file path when deleteFile called then file is deleted`() {
        val tempFile = createTempFile()
        assertTrue(tempFile.exists())

        deleteFile(tempFile.absolutePath)

        assertFalse(tempFile.exists())
    }

    @Test
    fun `given directory when deleteDirectory called then directory and its contents are deleted`() {
        val tempDir = createTempDir()
        val tempFileInDir = File(tempDir, "tempFile.txt").apply { createNewFile() }
        assertTrue(tempDir.exists())
        assertTrue(tempFileInDir.exists())

        deleteDirectory(tempDir)

        assertFalse(tempDir.exists())
        assertFalse(tempFileInDir.exists())
    }

    @Test
    fun `given an inexistent file should not throw`() {
        val file = File("imaginary-file.txt")

        assertDoesNotThrow {
            safeDeleteDirectory(file)
        }
    }

    @Test
    fun `given directory when deleteDirectory called then directory and its contents are deleted recursively`() {
        val tempDir = createTempDir()
        val innerTempDir = File(tempDir, "innerTempDir").apply { mkdir() }
        val tempFileInDir = File(innerTempDir, "tempFile.txt").apply { createNewFile() }

        assertTrue(tempDir.exists())
        assertTrue(innerTempDir.exists())
        assertTrue(tempFileInDir.exists())

        tempDir.deleteContentsRecursively()

        assertFalse(tempDir.exists())
        assertFalse(innerTempDir.exists())
        assertFalse(tempFileInDir.exists())
    }


}