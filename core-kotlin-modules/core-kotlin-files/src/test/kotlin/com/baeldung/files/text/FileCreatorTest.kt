package com.baeldung.files.text

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.io.File
import java.nio.file.Files

class FileCreatorTest {
    @TempDir
    lateinit var temporaryDirectory: File

    @Test
    fun `when createNewFile with existing parent then child file without content is created`() {
        // given
        val parentFile = temporaryDirectory.resolve("directory")
        parentFile.mkdir()
        val filename = "test.txt"

        // when
        val actual = FileCreator.createFileByCreateNewFile(
                parentFile = parentFile,
                filename = filename
        )

        // then
        assertThat(actual).exists()
        assertThat(actual).hasContent("")
    }


    @Test
    fun `when createTextFileUsingIO with existing parent then child file with content is created`() {
        // given
        val parentFile = temporaryDirectory.resolve("directory")
        parentFile.mkdir()
        val filename = "test.txt"
        val content = "Hello World"

        // when
        val actual = FileCreator.createFileByWriteText(
                parentFile = parentFile,
                filename = filename,
                content = content
        )

        // then
        assertThat(actual).exists()
        assertThat(actual).hasContent(content)
    }

    @Test
    fun `when createTextFileUsingNIO with existing parent then child file with content is created`() {
        // given
        val parentFile = temporaryDirectory.resolve("directory").toPath()
        Files.createDirectories(parentFile)
        val filename = "test.txt"
        val content = "Hello World"

        // when
        val actual = FileCreator.createFileByBufferedWriter(
                parentFile = parentFile,
                filename = filename,
                content = content
        )

        // then
        assertThat(actual).exists()
        assertThat(actual).hasContent(content)
    }

    @Test
    fun `when createFileUsingStream with existing parent then child file with content is created`() {
        // given
        val parentFile = temporaryDirectory.resolve("directory").toPath()
        Files.createDirectories(parentFile)
        val filename = "test.txt"
        val content = "Hello World"

        // when
        val actual = FileCreator.createFileUsingStream(
                parentFile = parentFile,
                filename = filename,
                inputStream = content.byteInputStream()
        )

        // then
        assertThat(actual).exists()
        assertThat(actual).hasContent(content)
    }

    @Test
    fun `when createFileUsingChannel with existing parent then child file with content is created`() {
        // given
        val parentFile = temporaryDirectory.resolve("directory")
        parentFile.mkdir()
        val filename = "test.txt"
        val content = "Hello World"

        // when
        val actual = FileCreator.createFileUsingChannel(
                parentFile = parentFile,
                filename = filename,
                content = content
        )

        // then
        assertThat(actual).exists()
        assertThat(actual).hasContent(content)
    }
}
