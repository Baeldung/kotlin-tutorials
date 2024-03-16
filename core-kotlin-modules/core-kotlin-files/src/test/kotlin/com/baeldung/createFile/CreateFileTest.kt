package com.baeldung.createFile

import com.baeldung.createfile.createEmptyFile
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class CreateFileTest {
    @Test
    fun `given file path when createEmptyFile called then file is created`() {
        val createdFile = createEmptyFile("createdFile.txt")

        assertTrue(createdFile.exists())
        createdFile.delete()
    }
}