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


}