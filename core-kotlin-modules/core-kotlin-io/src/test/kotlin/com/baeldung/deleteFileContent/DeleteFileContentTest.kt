package com.baeldung.deleteFileContent

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.File

class DeleteFileContentTest {
    private val fileName = "src/test/resources/example.txt"

    private val deleteFileContent = DeleteFileContent()

    @BeforeEach
    fun setUp() {
        File(fileName).writeText("Hello, World!")
    }

    @AfterEach
    fun tearDown() {
        File(fileName).delete()
    }

    @Test
    fun `Delete content with writeText`() {
        val file = File(fileName)
        deleteFileContent.withWriteText(file)

        assertThat(file.readText()).isEmpty()
    }

    @Test
    fun `Delete content with FileWriter`() {
        val file = File(fileName)
        deleteFileContent.withFileWriter(file)

        assertThat(file.readText()).isEmpty()
    }

    @Test
    fun `Delete content with PrintWriter`() {
        val file = File(fileName)
        deleteFileContent.withPrintWriter(file)

        assertThat(file.readText()).isEmpty()
    }

    @Test
    fun withFileOutputStream() {
        val file = File(fileName)
        deleteFileContent.withFileOutputStream(file)

        assertThat(file.readText()).isEmpty()
    }

    @Test
    fun withBufferedWriter() {
        val file = File(fileName)
        deleteFileContent.withBufferedWriter(file)

        assertThat(file.readText()).isEmpty()
    }

    @Test
    fun withRandomAccessFile() {
        val file = File(fileName)
        deleteFileContent.withRandomAccessFile(file)

        assertThat(file.readText()).isEmpty()
    }

    @Test
    fun withFileChannel() {
        val file = File(fileName)
        deleteFileContent.withFileChannel(file)

        assertThat(file.readText()).isEmpty()
    }

    @Test
    fun withFiles() {
        val file = File(fileName)
        deleteFileContent.withFiles(file)

        assertThat(file.readText()).isEmpty()
    }

    @Test
    fun withFileUtils() {
        val file = File(fileName)
        deleteFileContent.withFileUtils(file)

        assertThat(file.readText()).isEmpty()
    }
}